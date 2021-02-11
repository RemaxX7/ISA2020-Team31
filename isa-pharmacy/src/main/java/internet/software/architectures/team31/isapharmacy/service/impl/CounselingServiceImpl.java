package internet.software.architectures.team31.isapharmacy.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.domain.patient.Appointment;
import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentMedicineItem;
import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.domain.users.Dermatologist;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;
import internet.software.architectures.team31.isapharmacy.dto.CounselingCreateDTO;
import internet.software.architectures.team31.isapharmacy.domain.users.User;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;
import internet.software.architectures.team31.isapharmacy.dto.AdditionalExamSchedulingDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentFinalizationDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentScheduleDTO;
import internet.software.architectures.team31.isapharmacy.dto.CounselingCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;
import internet.software.architectures.team31.isapharmacy.repository.CounselingRepository;
import internet.software.architectures.team31.isapharmacy.service.AppointmentService;
import internet.software.architectures.team31.isapharmacy.service.CounselingService;
import internet.software.architectures.team31.isapharmacy.service.EmailService;
import internet.software.architectures.team31.isapharmacy.service.ExamService;
import internet.software.architectures.team31.isapharmacy.service.PharmacistService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@Service
public class CounselingServiceImpl implements CounselingService {

	@Autowired
	private CounselingRepository counselingRepository;
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
	private AppointmentService appointmentService;
	@Autowired
	private MedicineServiceImpl medicineService;
	@Autowired
	private PharmacistService pharmacistService;
	@Autowired
	private ExamService examService;
	@Autowired
	private EmailService emailService;

	@Override
	public Counseling save(CounselingCreateDTO dto) {
		Counseling counseling = new Counseling(dto);
		counseling.setPharmacy(pharmacyService.findById(dto.getPharmacyId()));
		counseling.setPharmacist((Pharmacist) userService.findById(dto.getPharmacistId()));
		return counselingRepository.save(counseling);
	}

	@Override
	public Counseling schedule(AppointmentScheduleDTO dto) throws PenaltyException, AppointmentNotFreeException {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if(patient.getPenalty() >= 3) {
			throw new PenaltyException("Cannot schedule counseling. Maximum number of penalties exceeded.");
		}
		
		Counseling counseling = findById(dto.getAppointmentId());
		if(counseling.getAppointmentStatus() != AppointmentStatus.FREE) {
			throw new AppointmentNotFreeException("Appointment term is not available.");
		}
		
		counseling.setPatient(patient);
		sendCounselingEmail(counseling);
		return counselingRepository.save(counseling);
	}

	@Override
	public Counseling cancel(Long id) throws CancelAppointmentException {
		Counseling counseling = findById(id);
		LocalDateTime currentDateTime = LocalDateTime.now();
		if(!currentDateTime.plusDays(1).isBefore(counseling.getDateRange().getStartDateTime())) {
			throw new CancelAppointmentException("Counseling cannot be cancelled 24 hours before start.");
		}
		counseling.setPatient(null);
		counseling.setAppointmentStatus(AppointmentStatus.FREE);
		return counselingRepository.save(counseling);
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
	public Counseling findById(Long id) {
		return counselingRepository.findById(id).orElse(null);
	}

	@Override
	public boolean hasPatientVisitedPharmacist(Long patientId, Long pharmacistId) {
		return counselingRepository.findOneByPatientIdAndPharmacistIdAndAppointmentStatus(patientId, pharmacistId, AppointmentStatus.FINISHED) != null;
	}
	@Override
	public Counseling finalizeExam(AppointmentFinalizationDTO dto) {
		List<Counseling> counseling = (List<Counseling>) findAll();
		List<AppointmentMedicineItem> itemList = new ArrayList<AppointmentMedicineItem>();
		List<Medicine>medicineList = new ArrayList<Medicine>();
		for (String med : dto.getMedicine()) {
			medicineList.add(medicineService.findByName(med));
		}
		for (Counseling couns : counseling) {
			if(couns.getPatient().getUidn().equals(dto.getUidn())) {
				for (Medicine medicine : medicineList) {
					itemList.add(new AppointmentMedicineItem(medicine,3));
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
				+ counseling.getPharmacist().getFullName() + " has been scheduled.");
		text.append("Pharmacy: " + counseling.getPharmacy().getName() + "\r\n");
		text.append("Address: " + counseling.getPharmacy().getAddress() + "\r\n");
		text.append("Date and time: " + counseling.getDateRange().getStartDateTime() + " - " + counseling.getDateRange().getEndDateTime() + "\r\n");
		return text.toString();
	}

	@Override
	public Counseling scheduleAdditionalConsultation(AdditionalExamSchedulingDTO dto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		Patient patient = patientService.findByUidn(dto.getUidn());
		LocalDateTime date = LocalDateTime.parse(dto.getDate(),formatter);
		DateRange range = new DateRange();
		range.setStartDateTime(date);
		range.setEndDateTime(date.plusMinutes(30));
		Pharmacist pharm = (Pharmacist) userService.findByUidn(dto.getEmployeeuidn());
		Counseling counseling = new Counseling();
		counseling.setPharmacist(pharm);
		counseling.setPharmacy(pharm.getPharmacy());
		counseling.setPatient(patient);
		counseling.setAppointmentStatus(AppointmentStatus.FREE);
		counseling.setDateRange(range);
		sendCounselingEmail(counseling);
		return counselingRepository.save(counseling);
	}

	@Override
	public List<String> findTerminsByUidnsPharm(String patuidn, String empuidn) {
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
		frontList.removeAll(backList);
		return frontList;
	}

	@Override
	public List<Counseling> findCounsForPharm(String uidn,String days) {
		List<Counseling>counsList = (List<Counseling>) counselingService.findAll();
		List<Counseling>frontList = new ArrayList<Counseling>();
		User user = (Pharmacist)userService.findByUidn(uidn);
		for (Counseling counseling : counsList) {
			if(counseling.getPharmacist().getUidn().equals(user.getUidn()) && counseling.getDateRange().getStartDateTime().isBefore(LocalDateTime.now().plusDays(Long.parseLong(days)))) {
				frontList.add(counseling);
			}
		}
		return frontList;
	}

	@Override
	public List<Exam> findExamsForDerm(String uidn, String days) {
		List<Exam> examsList = (List<Exam>) examService.findAll();
		List<Exam>frontList = new ArrayList<Exam>();
		User user = (Dermatologist)userService.findByUidn(uidn);
		for (Exam exam : examsList) {
			if(exam.getDermatologist().getUidn().equals(user.getUidn()) && exam.getDateRange().getStartDateTime().isBefore(LocalDateTime.now().plusDays(Long.parseLong(days)))) {
				frontList.add(exam);
			}
		}
		return frontList;
	}
}
