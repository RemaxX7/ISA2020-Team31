package internet.software.architectures.team31.isapharmacy.service.impl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.domain.patient.Appointment;
import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentMedicineItem;
import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.InventoryItem;
import internet.software.architectures.team31.isapharmacy.domain.schedule.Shift;
import internet.software.architectures.team31.isapharmacy.domain.users.Dermatologist;
import internet.software.architectures.team31.isapharmacy.domain.users.Employee;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;
import internet.software.architectures.team31.isapharmacy.domain.users.User;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;
import internet.software.architectures.team31.isapharmacy.dto.AdditionalExamSchedulingDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentFinalizationDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentViewDTO;
import internet.software.architectures.team31.isapharmacy.dto.CounselingCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.UserViewDTO;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.CounselingAlreadyScheduledException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidInputException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;
import internet.software.architectures.team31.isapharmacy.exception.ShiftNotFreeEception;
import internet.software.architectures.team31.isapharmacy.exception.UserNotTypePatientException;
import internet.software.architectures.team31.isapharmacy.repository.CounselingRepository;
import internet.software.architectures.team31.isapharmacy.repository.UserRepository;
import internet.software.architectures.team31.isapharmacy.service.AppointmentService;
import internet.software.architectures.team31.isapharmacy.service.CounselingService;
import internet.software.architectures.team31.isapharmacy.service.EmailService;
import internet.software.architectures.team31.isapharmacy.service.ExamService;
import internet.software.architectures.team31.isapharmacy.service.PharmacistService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;
import internet.software.architectures.team31.isapharmacy.service.ShiftService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@Service
public class CounselingServiceImpl implements CounselingService {

	@Autowired
	private CounselingRepository counselingRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CounselingService counselingService;
	@Autowired
	private PharmacyService pharmacyService;
	@Autowired
	private UserService userService;
	@Autowired
	private MedicineServiceImpl medicineService;
	@Autowired
	private PatientServiceImpl patientService;
	@Autowired
	private ShiftService shiftService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private PharmacistService pharmacistService;
	@Autowired
	private ExamService examService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private InventoryItemServiceImpl inventoryService;
	@Override
	public Counseling save(CounselingCreateDTO dto) throws PenaltyException, CounselingAlreadyScheduledException {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if(patient.getPenalty() >= 3) {
			throw new PenaltyException("Cannot schedule a counseling appointment. Maximum number of penalties exceeded.");
		}
		
		if(hasPatientAlreadyScheduledCounseling(patient.getId(), dto.getPharmacistId())) {
			throw new CounselingAlreadyScheduledException("You have already scheduled a counseling appointment with this pharmacist.");
		}
		
		
		Counseling counseling = new Counseling(dto);
		counseling.setPharmacy(pharmacyService.findById(dto.getPharmacyId()));
		counseling.setPharmacist((Pharmacist) userService.findById(dto.getPharmacistId()));
		counseling.setAppointmentStatus(AppointmentStatus.OCCUPIED);
		counseling.setPatient(patient);
		//TODO: Set price here
		sendCounselingEmail(counseling);
		return counselingRepository.save(counseling);
	}

	@Override
	public AppointmentViewDTO schedule(Long id) throws PenaltyException, AppointmentNotFreeException, CounselingAlreadyScheduledException {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if(patient.getPenalty() >= 3) {
			throw new PenaltyException("Cannot schedule a counseling appointment. Maximum number of penalties exceeded.");
		}
		
		Counseling counseling = findById(id);
		if(hasPatientAlreadyScheduledCounseling(patient.getId(), counseling.getPharmacist().getId())) {
			throw new CounselingAlreadyScheduledException("You have already scheduled a counseling appointment with this pharmacist.");
		}
		
		if(counseling.getAppointmentStatus() != AppointmentStatus.FREE) {
			throw new AppointmentNotFreeException("Appointment term is not available.");
		}
		
		counseling.setPatient(patient);
		counseling.setAppointmentStatus(AppointmentStatus.OCCUPIED);
		sendCounselingEmail(counseling);
		return new AppointmentViewDTO(counselingRepository.save(counseling));
	}

	@Override
	public Boolean cancel(Long id) throws CancelAppointmentException {
		Counseling counseling = findById(id);
		LocalDateTime currentDateTime = LocalDateTime.now();
		if(!currentDateTime.plusDays(1).isBefore(counseling.getDateRange().getStartDateTime())) {
			throw new CancelAppointmentException("This counseling cannot be cancelled.");
		}
		counselingRepository.delete(counseling);
		return Boolean.TRUE;
	}
	
	@Override
	public Collection<Counseling> findAll() {
		return counselingRepository.findAll();
	}

	@Override
	public Collection<Counseling> findAllByPatientId(Long id) {
		return counselingRepository.findAllByPatientId(id);
	}

	@Override
	public Collection<Counseling> findAllByPharmacistId(Long id) {
		return counselingRepository.findAllByPharmacistId(id);
	}
	
	@Override
	public Collection<Counseling> findAllByAppointmentStatus(AppointmentStatus status) {
		return counselingRepository.findAllByAppointmentStatus(status);
	}
	
	@Override
	public Collection<Counseling> findAllByPatientIdAndAppointmentStatus(Long patientId, AppointmentStatus status) {
		return counselingRepository.findAllByPatientIdAndAppointmentStatus(patientId, status);
	}
	
	@Override
	public Page<AppointmentViewDTO> findAllByPatientIdAndAppointmentStatus(AppointmentStatus status, Pageable pageable) {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return counselingRepository.findAllByPatientIdAndAppointmentStatus(patient.getId(), status, pageable).map(counseling -> new AppointmentViewDTO(counseling));
	}

	@Override
	public Counseling findById(Long id) {
		return counselingRepository.findById(id).orElse(null);
	}

	@Override
	public boolean hasPatientVisitedPharmacist(Long patientId, Long pharmacistId) {
		return counselingRepository.existsByPatientIdAndPharmacistIdAndAppointmentStatus(patientId, pharmacistId, AppointmentStatus.FINISHED);
	}
		
	@Override
	public boolean hasPatientAlreadyScheduledCounseling(Long patientId, Long pharmacistId) {
		return counselingRepository.existsByPatientIdAndPharmacistIdAndAppointmentStatus(patientId, pharmacistId, AppointmentStatus.OCCUPIED);
	}

	@Override
	public Counseling finalizeExam(AppointmentFinalizationDTO dto,String examid,String quant) throws InvalidInputException{
		List<Counseling> counseling = (List<Counseling>) findAll();
		List<AppointmentMedicineItem> itemList = new ArrayList<AppointmentMedicineItem>();
		List<InventoryItem>inventoryItemList = inventoryService.findAll();
		for (Counseling couns : counseling) {
			if(couns.getPatient().getUidn().equals(dto.getUidn()) && (couns.getAppointmentStatus().equals(AppointmentStatus.OCCUPIED) || couns.getAppointmentStatus().equals(AppointmentStatus.FREE)) && couns.getId().equals(Long.parseLong(examid))) {
				for (String item : dto.getMedicine()) {
					Medicine med = medicineService.findByName(item.split(",")[0]);
					for (InventoryItem inventoryItem : inventoryItemList) {
						if(inventoryItem.getMedicine().getId().equals(med.getId()) && inventoryItem.getQuantity() >= Integer.parseInt(item.split(",")[1])) {
							itemList.add(new AppointmentMedicineItem(med,Integer.parseInt(item.split(",")[1])));
							inventoryItem.setQuantity(inventoryItem.getQuantity()-Integer.parseInt(item.split(",")[1]));
							inventoryService.save(inventoryItem);
							break;
						}
						if(inventoryItem.getMedicine().getId().equals(med.getId()) && inventoryItem.getQuantity() < Integer.parseInt(item.split(",")[1]))
							throw new InvalidInputException("Not enough medicine in stock.");
					}
				}
				couns.setAppointmentStatus(AppointmentStatus.FINISHED);
				couns.setReport(dto.getReport());
				couns.setAppointmentMedicineItems(itemList);
				return counselingRepository.save(couns);
			}
		}
		return null;
	}
	
	private void sendCounselingEmail(Counseling counseling) {
		emailService.sendEmail(counseling.getPatient().getEmail(), "Counseling appointment confirmation", getCounselingEmailText(counseling));
	}
	
	private String getCounselingEmailText(Counseling counseling) {
		StringBuilder text = new StringBuilder("Hello, " + counseling.getPatient().getName() + ". Your pharmacist counseling appointment with "
				+ counseling.getPharmacist().getFullName() + " has been scheduled.\r\n");
		text.append("Pharmacy: " + counseling.getPharmacy().getName() + "\r\n");
		text.append("Address: " + counseling.getPharmacy().getAddress() + "\r\n");
		text.append("Date and time: " + counseling.getDateRange().getStartDateTime() + " - " + counseling.getDateRange().getEndDateTime() + "\r\n");
		return text.toString();
	}
	@Transactional
	@Override
	public Counseling scheduleAdditionalConsultation(AdditionalExamSchedulingDTO dto) throws ShiftNotFreeEception, UserNotTypePatientException {
		List<Shift> shiftList = shiftService.findAllByEmployeeUidn(dto.getEmployeeuidn());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		Pharmacist pharm = (Pharmacist) userService.findByUidn(dto.getEmployeeuidn());
		User patient = userService.findByUidn(dto.getUidn());
		if(patient.getUidn().equals(dto.getEmployeeuidn())) {
			throw new UserNotTypePatientException("You cannot schedule an appointment for yourself.");
		}
		LocalDateTime date = LocalDateTime.parse(dto.getDate(),formatter);
		for (Shift shift : shiftList) {
			if(date.isAfter(shift.getInterval().getStartDateTime()) && date.isBefore(shift.getInterval().getEndDateTime())) {
				DateRange range = new DateRange();
				range.setStartDateTime(date);
				range.setEndDateTime(date.plusMinutes(30));
				Counseling counseling = new Counseling();
				counseling.setPharmacist(pharm);
				counseling.setPharmacy(pharm.getPharmacy());
				try {
					counseling.setPatient((Patient) patient);
				}catch(Exception e){
					throw new UserNotTypePatientException("You cannot schedule an appointment for employees.");
				}
				counseling.setAppointmentStatus(AppointmentStatus.FREE);
				counseling.setDateRange(range);
				sendCounselingEmail(counseling);
				userRepository.saveAndFlush(pharm);
				return counselingRepository.save(counseling);
			}
		}
		throw new ShiftNotFreeEception("Termin not in your shift or already booked.");
		
	}

	@Override
	public boolean areThereAvailablePharmacists(Shift shift, LocalDateTime dateTime) {
		return isPharmacistAvailable(shift.getEmployee().getId(), dateTime);
	}
	
	@Override
	public Collection<UserViewDTO> findAvailablePharmacists(LocalDateTime dateTime, Long pharmacyId) {
		Collection<Shift> shifts = shiftService.findAllByDateAndPharmacyId(dateTime, pharmacyId);
		List<Employee> pharmacists = new ArrayList<Employee>();
		for(Shift shift: shifts) {
			if(isPharmacistAvailable(shift.getEmployee().getId(), dateTime)) {
				pharmacists.add(shift.getEmployee());
			}
		}
		return pharmacists.stream().map(pharmacist -> new UserViewDTO(pharmacist)).collect(Collectors.toList());
	}
	
	private boolean isPharmacistAvailable(Long pharmacistId, LocalDateTime dateTime) {
		Collection<Counseling> counselings = counselingRepository.findAllByPharmacistIdAndAppointmentStatus(pharmacistId, AppointmentStatus.OCCUPIED);
		for(Counseling counseling: counselings) {
			if(counseling.getDateRange().getStartDateTime().equals(dateTime)) {
				return false;
			}
		}
		return true;
	}
	
	public List<String> findTerminsByUidnsPharm(String patuidn, String empuidn) {
		User employee = userService.findByUidn(empuidn);
		User patient = userService.findByUidn(patuidn);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		List<Shift> shiftList = shiftService.findAllByEmployeeUidn(empuidn);
		DateRange range = new DateRange();
		DateRange range2 = new DateRange();
		DateRange range3 = new DateRange();
		DateRange range4 = new DateRange();
		DateRange range5 = new DateRange();
		DateRange range6 = new DateRange();
		DateRange range7 = new DateRange();
		DateRange range8 = new DateRange();

		String date7 = "2024-03-02 13:00";
		String date3 = "2022-03-02 13:00";
		String date5 = "2023-03-02 13:00";
		String date4 = "2022-07-02 14:00";
		String date6 = "2023-07-02 15:00";
		String date8 = "2024-07-02 16:00";
		String date1 = "2021-08-27 12:00";
		String date2 = "2021-10-17 17:00";
		LocalDateTime date11 = LocalDateTime.parse(date1,formatter);
		LocalDateTime date12 = LocalDateTime.parse(date2,formatter);
		LocalDateTime date13 = LocalDateTime.parse(date3,formatter);
		LocalDateTime date14 = LocalDateTime.parse(date4,formatter);
		LocalDateTime date15 = LocalDateTime.parse(date5,formatter);
		LocalDateTime date16 = LocalDateTime.parse(date6,formatter);
		LocalDateTime date17 = LocalDateTime.parse(date7,formatter);
		LocalDateTime date18 = LocalDateTime.parse(date8,formatter);
		range.setStartDateTime(date11);
		range.setEndDateTime(date11.plusMinutes(30));
		range2.setStartDateTime(date12);
		range2.setEndDateTime(date12.plusMinutes(30));
		range3.setStartDateTime(date13);
		range3.setEndDateTime(date13.plusMinutes(30));
		range4.setStartDateTime(date14);
		range4.setEndDateTime(date14.plusMinutes(30));
		range5.setStartDateTime(date15);
		range5.setEndDateTime(date15.plusMinutes(30));
		range6.setStartDateTime(date16);
		range6.setEndDateTime(date16.plusMinutes(30));
		range7.setStartDateTime(date17);
		range7.setEndDateTime(date17.plusMinutes(30));
		range8.setStartDateTime(date18);
		range8.setEndDateTime(date18.plusMinutes(30));
		ArrayList<DateRange> dateList = new ArrayList<DateRange>();
		dateList.add(range);
		dateList.add(range2);
		dateList.add(range3);
		dateList.add(range4);
		dateList.add(range5);
		dateList.add(range6);
		dateList.add(range7);
		dateList.add(range8);
		
		List<Counseling> counsList = (List<Counseling>) counselingService.findAll();
		List<String> backList = new ArrayList<String>();
		List<String> frontList = new ArrayList<String>();
		for (DateRange string : dateList) {
			String[] comb = string.getStartDateTime().toString().split("T");
			frontList.add(comb[0]+" "+comb[1]);
		}
		for (DateRange dr : dateList) {
			for (Counseling couns : counsList) {
					if(couns.getPharmacist().getUidn().equals(employee.getUidn())) {
						if(couns.getDateRange().getStartDateTime().equals(dr.getStartDateTime())) {
								String[] comb = dr.getStartDateTime().toString().split("T");
								backList.add(comb[0]+" "+comb[1]);
						}
					}
			}
		}
			for (DateRange drn : dateList) {
				for (Appointment ap : appointmentService.findAll()) {
					if(ap.getPatient().getUidn().equals(patient.getUidn())) {
						if(ap.getDateRange().getStartDateTime().equals(drn.getStartDateTime())) {
							String[] comb = drn.getStartDateTime().toString().split("T");
							backList.add(comb[0]+" "+comb[1]);
						}
				}
			}
		}
			for (DateRange dr : dateList) {
				for (Shift  shift : shiftList) {
					if(dr.getStartDateTime().isBefore(shift.getInterval().getStartDateTime()) || dr.getStartDateTime().isAfter(shift.getInterval().getEndDateTime())){
						String[] comb = dr.getStartDateTime().toString().split("T");
						backList.add(comb[0]+" "+comb[1]);
					}
				}
			}
		frontList.removeAll(backList);
		return frontList;
	}

	@Override
	public List<Counseling> findCounsForPharm(String uidn,String days) {
		List<Counseling>counsList = (List<Counseling>) counselingService.findAllByOrderByStartDateTimeAsc();
		List<Counseling>frontList = new ArrayList<Counseling>();
		User user = (Pharmacist)userService.findByUidn(uidn);
		for (Counseling counseling : counsList) {
			if(counseling.getPharmacist().getUidn().equals(user.getUidn()) && !(counseling.getAppointmentStatus().equals(AppointmentStatus.FINISHED) || counseling.getAppointmentStatus().equals(AppointmentStatus.UNATTENDED)) && counseling.getDateRange().getStartDateTime().isAfter(LocalDateTime.now()) && counseling.getDateRange().getEndDateTime().isBefore(LocalDateTime.now().plusDays(Long.parseLong(days)))) {
				frontList.add(counseling);
			}
		}
		return frontList;
	}

	

	@Override
	public Collection<Counseling> findAllActive(String uidn) {
		List<Counseling> counsList = counselingRepository.findAllByOrderByDateRangeAsc();
		List<Counseling> frontList = new ArrayList<Counseling>();
		Pharmacist pharm = (Pharmacist) userService.findByUidn(uidn);
		for (Counseling couns : counsList) {
			if((couns.getAppointmentStatus().equals(AppointmentStatus.FREE) || couns.getAppointmentStatus().equals(AppointmentStatus.OCCUPIED)) && couns.getDateRange().getStartDateTime().isAfter(LocalDateTime.now().minusHours(1))&& couns.getPharmacist().getUidn().equals(pharm.getUidn())) {
				frontList.add(couns);
			}
		}
		return frontList;
	}
	
	@Override
	public Counseling updatePoints(Long counselingId, Integer points) {
		Counseling counseling = counselingRepository.getOne(counselingId);
		
		if (points >= 0) {
			counseling.setPoints(points);
			return counselingRepository.save(counseling);
		}
		
		return counseling;
	}

	@Override
	public List<Patient> findCheckedPatients(String uidn) {
		List<Counseling> counsList = counselingRepository.findAll();
		Pharmacist pharm = (Pharmacist) userService.findByUidn(uidn);
		List<Patient> frontList = new ArrayList<Patient>();
		for (Counseling counseling : counsList) {
			if(counseling.getAppointmentStatus().equals(AppointmentStatus.FINISHED) && pharm.getId().equals(counseling.getPharmacist().getId())) {
				
				frontList.add(counseling.getPatient());
			}
		}
		return frontList;
	}

	@Override
	public List<Counseling> findAllByOrderByStartDateTimeAsc() {
		return counselingRepository.findAllByOrderByDateRangeAsc();
	}
}
