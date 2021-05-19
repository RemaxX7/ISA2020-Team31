package internet.software.architectures.team31.isapharmacy.service.impl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.domain.patient.Appointment;
import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentMedicineItem;
import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.InventoryItem;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.domain.schedule.Shift;
import internet.software.architectures.team31.isapharmacy.domain.users.Dermatologist;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;
import internet.software.architectures.team31.isapharmacy.domain.users.User;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;
import internet.software.architectures.team31.isapharmacy.dto.AdditionalExamSchedulingDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentFinalizationDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentViewDTO;
import internet.software.architectures.team31.isapharmacy.dto.ExamCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidInputException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;
import internet.software.architectures.team31.isapharmacy.exception.ShiftNotFreeEception;
import internet.software.architectures.team31.isapharmacy.repository.ExamRepository;
import internet.software.architectures.team31.isapharmacy.service.AppointmentService;
import internet.software.architectures.team31.isapharmacy.service.CounselingService;
import internet.software.architectures.team31.isapharmacy.service.DermatologistService;
import internet.software.architectures.team31.isapharmacy.service.EmailService;
import internet.software.architectures.team31.isapharmacy.service.ExamService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;
import internet.software.architectures.team31.isapharmacy.service.ShiftService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private ExamService examService;
	@Autowired
	private PharmacyService pharmacyService;
	@Autowired
	private UserService userService;
	@Autowired
	private MedicineServiceImpl medicineService;
	@Autowired
	private PatientServiceImpl patientService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private InventoryItemServiceImpl inventoryService;
	@Autowired
	private ShiftService shiftService;
	@Override
	public Exam save(ExamCreateDTO dto) {
		Exam exam = new Exam(dto);
		exam.setPharmacy(pharmacyService.findById(dto.getPharmacyId()));
		exam.setDermatologist((Dermatologist) userService.findById(dto.getDermatologistId()));
		exam.setAppointmentStatus(AppointmentStatus.FREE);
		return examRepository.save(exam);
	}
	
	@Override
	public AppointmentViewDTO schedule(Long id) throws PenaltyException, AppointmentNotFreeException {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if(patient.getPenalty() >= 3) {
			throw new PenaltyException("Cannot schedule an exam. Maximum number of penalties exceeded.");
		}
		
		Exam exam = findById(id);
		if(exam.getAppointmentStatus() != AppointmentStatus.FREE) {
			throw new AppointmentNotFreeException("Appointment term is not available.");
		}
		
		exam.setPatient(patient);
		exam.setAppointmentStatus(AppointmentStatus.OCCUPIED);
		sendExamEmail(exam);
		return new AppointmentViewDTO(examRepository.save(exam));
	}

	@Override
	public AppointmentViewDTO cancel(Long id) throws CancelAppointmentException {
		Exam exam = findById(id);
		LocalDateTime currentDateTime = LocalDateTime.now();
		if(!currentDateTime.plusDays(1).isBefore(exam.getDateRange().getStartDateTime())) {
			throw new CancelAppointmentException("This exam cannot be cancelled.");
		}
		exam.setPatient(null);
		exam.setAppointmentStatus(AppointmentStatus.FREE);
		return new AppointmentViewDTO(examRepository.save(exam));
	}

	@Override
	public Collection<Exam> findAll() {
		return examRepository.findAll();
	}

	@Override
	public Collection<Exam> findAllByPatientId(Long id) {
		return examRepository.findAllByPatientId(id);
	}

	@Override
	public Collection<Exam> findAllByDermatologistId(Long id) {
		return examRepository.findAllByDermatologistId(id);
	}
	
	@Override
	public Collection<Exam> findAllByAppointmentStatus(AppointmentStatus status) {
		return examRepository.findAllByAppointmentStatus(status);
	}
	
	@Override
	public Page<AppointmentViewDTO> findAllByAppointmentStatus(AppointmentStatus status, Pageable pageable) {
		return examRepository.findAllByAppointmentStatus(status, pageable).map(exam -> new AppointmentViewDTO(exam));
	}

	@Override
	public Collection<Exam> findAllByPatientIdAndAppointmentStatus(Long patientId, AppointmentStatus status) {
		return examRepository.findAllByPatientIdAndAppointmentStatus(patientId, status);
	}
	
	@Override
	public Page<AppointmentViewDTO> findAllByPatientIdAndAppointmentStatus(AppointmentStatus status, Pageable pageable) {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return examRepository.findAllByPatientIdAndAppointmentStatus(patient.getId(), status, pageable).map(exam -> new AppointmentViewDTO(exam));
	}
	
	@Override
	public Exam findById(Long id) {
		return examRepository.findById(id).orElse(null);
	}

	@Override
	public boolean hasPatientVisitedDermatologist(Long patientId, Long dermatologistId) {
		return examRepository.existsByPatientIdAndDermatologistIdAndAppointmentStatus(patientId, dermatologistId, AppointmentStatus.FINISHED);
	}

	@Override
	public Exam finalizeExam(AppointmentFinalizationDTO dto,String examid,String quant) throws InvalidInputException{
		List<Exam> exam = (List<Exam>) findAll();
		List<AppointmentMedicineItem> itemList = new ArrayList<AppointmentMedicineItem>();
		List<InventoryItem>inventoryItemList = inventoryService.findAll();
		for (Exam ex : exam) {
			if(ex.getPatient().getUidn().equals(dto.getUidn()) && ex.getAppointmentStatus().equals(AppointmentStatus.OCCUPIED) && ex.getId().equals(Long.parseLong(examid))) {
				for (String item : dto.getMedicine()) {
					Medicine med = medicineService.findByName(item.split(",")[0]);
					for (InventoryItem inventoryItem : inventoryItemList) {
						if(inventoryItem.getMedicine().getId().equals(med.getId()) && inventoryItem.getQuantity() > Integer.parseInt(item.split(",")[1])) {
							itemList.add(new AppointmentMedicineItem(med,Integer.parseInt(item.split(",")[1])));
							break;
						}
						else if(inventoryItem.getQuantity() < Integer.parseInt(item.split(",")[1]))
							throw new InvalidInputException("Not enough medicine in stock.");
					}
				}
				ex.setAppointmentStatus(AppointmentStatus.FINISHED);
				ex.setReport(dto.getReport());
				ex.setAppointmentMedicineItems(itemList);
				return examRepository.save(ex);
			}
		}
		return null;
	}
	
	private void sendExamEmail(Exam exam) {
		emailService.sendEmail(exam.getPatient().getEmail(), "Dermatologist appointment confirmation", getExamEmailText(exam));
	}
	
	private String getExamEmailText(Exam exam) {
		StringBuilder text = new StringBuilder("Hello, " + exam.getPatient().getName() + ". Your dermatologist exam appointment with "
				+ exam.getDermatologist().getFullName() + " has been scheduled.");
		text.append("Pharmacy: " + exam.getPharmacy().getName() + "\r\n");
		text.append("Address: " + exam.getPharmacy().getAddress() + "\r\n");
		text.append("Date and time: " + exam.getDateRange().getStartDateTime() + " - " + exam.getDateRange().getEndDateTime() + "\r\n");
		return text.toString();
	}

	@Override
	public Exam scheduleAdditionalExam(AdditionalExamSchedulingDTO dto) throws ShiftNotFreeEception{
		List<Shift> shiftList = shiftService.findAllByEmployeeUidn(dto.getEmployeeuidn());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		Patient patient = patientService.findByUidn(dto.getUidn());
		LocalDateTime date = LocalDateTime.parse(dto.getDate(),formatter);
		for (Shift shift : shiftList) {
			if(date.isAfter(shift.getInterval().getStartDateTime()) && date.isBefore(shift.getInterval().getEndDateTime())) {
				DateRange range = new DateRange();
				range.setStartDateTime(date);
				range.setEndDateTime(date.plusMinutes(30));
				Dermatologist derm = (Dermatologist) userService.findByUidn(dto.getEmployeeuidn());
				Exam examInProgress = findById(Long.parseLong(dto.getId()));
				Exam exam = new Exam();
				exam.setDermatologist(derm);
				exam.setPharmacy(examInProgress.getPharmacy());
				exam.setPatient(patient);
				exam.setAppointmentStatus(AppointmentStatus.FREE);
				exam.setDateRange(range);
				sendExamEmail(exam);
				return examRepository.save(exam);
			}
		}
		throw new ShiftNotFreeEception("Termin not in your shift.");
	}


	@Override
	public List<String> findTerminsByUidns(String patuidn, String empuidn) {
		User employee = userService.findByUidn(empuidn);
		User patient = userService.findByUidn(patuidn);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		DateRange range = new DateRange();
		DateRange range2 = new DateRange();
		DateRange range3 = new DateRange();

		String date2 = "2021-03-02 13:00";
		String date1 = "2020-03-02 13:00";
		String date3 = "2022-03-02 13:00";
		LocalDateTime date11 = LocalDateTime.parse(date1,formatter);
		LocalDateTime date12 = LocalDateTime.parse(date2,formatter);
		LocalDateTime date13 = LocalDateTime.parse(date3,formatter);
		range.setStartDateTime(date11);
		range.setEndDateTime(date11.plusMinutes(30));
		range2.setStartDateTime(date12);
		range2.setEndDateTime(date12.plusMinutes(30));
		range3.setStartDateTime(date13);
		range3.setEndDateTime(date13.plusMinutes(30));
		ArrayList<DateRange> dateList = new ArrayList<DateRange>();
		dateList.add(range);
		dateList.add(range2);
		dateList.add(range3);
		
		List<Exam> examList = (List<Exam>) examService.findAll();
		List<String> backList = new ArrayList<String>();
		List<String> frontList = new ArrayList<String>();
		for (DateRange string : dateList) {
			String[] comb = string.getStartDateTime().toString().split("T");
			frontList.add(comb[0]+" "+comb[1]);
		}
		for (DateRange dr : dateList) {
			for (Exam exam : examList) {
					if(exam.getDermatologist().getUidn().equals(employee.getUidn())) {
						if(exam.getDateRange().getStartDateTime().equals(dr.getStartDateTime())) {
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
		frontList.removeAll(backList);
		return frontList;
}

	@Override
	public Collection<Exam> findAllActive() {
		List<Exam> examList = examRepository.findAll();
		List<Exam> frontList = new ArrayList<Exam>();
		for (Exam exam : examList) {
			if((exam.getAppointmentStatus().equals(AppointmentStatus.FREE) || exam.getAppointmentStatus().equals(AppointmentStatus.OCCUPIED)) && exam.getDateRange().getStartDateTime().isAfter(LocalDateTime.now().minusHours(1))) {
				frontList.add(exam);
			}
		}
		return frontList;
	}
	
	@Override
	public Exam updatePoints(Long examId, Integer points) {
		Exam exam = examRepository.getOne(examId);
		
		if (points >= 0) {
			exam.setPoints(points);
			return examRepository.save(exam);
		}
		
		return exam;
	}

	@Override
	public List<Patient> findCheckedPatientsDermatologist(String uidn) {
		List<Exam> examList = examRepository.findAll();
		Dermatologist derm = (Dermatologist) userService.findByUidn(uidn);
		List<Patient> frontList = new ArrayList<Patient>();
		for (Exam ex : examList) {
			if(ex.getAppointmentStatus().equals(AppointmentStatus.FINISHED) && derm.getId().equals(ex.getDermatologist().getId())) {
				if(!frontList.contains(ex.getPatient()))
					frontList.add(ex.getPatient());
			}
		}
		return frontList;
	}
}
