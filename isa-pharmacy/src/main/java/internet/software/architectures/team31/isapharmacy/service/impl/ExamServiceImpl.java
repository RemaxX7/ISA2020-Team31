package internet.software.architectures.team31.isapharmacy.service.impl;

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
import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentMedicineItem;
import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.domain.users.Dermatologist;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;
import internet.software.architectures.team31.isapharmacy.dto.AdditionalExamSchedulingDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentFinalizationDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentViewDTO;
import internet.software.architectures.team31.isapharmacy.dto.ExamCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;
import internet.software.architectures.team31.isapharmacy.repository.ExamRepository;
import internet.software.architectures.team31.isapharmacy.service.EmailService;
import internet.software.architectures.team31.isapharmacy.service.ExamService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private PharmacyService pharmacyService;
	@Autowired
	private UserService userService;
	@Autowired
	private PatientServiceImpl patientService;
	@Autowired
	private MedicineServiceImpl medicineService;
	@Autowired
	private EmailService emailService;

	@Override
	public Exam save(ExamCreateDTO dto) {
		Exam exam = new Exam(dto);
		exam.setPharmacy(pharmacyService.findById(dto.getPharmacyId()));
		exam.setDermatologist((Dermatologist) userService.findById(dto.getDermatologistId()));
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
	public Exam finalizeExam(AppointmentFinalizationDTO dto) {
		List<Exam> exam = (List<Exam>) findAll();
		List<AppointmentMedicineItem> itemList = new ArrayList<AppointmentMedicineItem>();
		List<Medicine>medicineList = new ArrayList<Medicine>();
		for (String med : dto.getMedicine()) {
			medicineList.add(medicineService.findByName(med));
		}
		for (Exam ex : exam) {
			if(ex.getPatient().getUidn().equals(dto.getUidn())) {
				for (Medicine medicine : medicineList) {
					itemList.add(new AppointmentMedicineItem(medicine,3));
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
	public Exam scheduleAdditionalExam(AdditionalExamSchedulingDTO dto){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Patient patient = patientService.findByUidn(dto.getUidn());
		LocalDateTime date = LocalDateTime.parse(dto.getDate(),formatter);
		DateRange range = new DateRange();
		range.setStartDateTime(date);
		range.setEndDateTime(date.plusMinutes(30));
		Dermatologist derm = (Dermatologist) userService.findByUidn(dto.getEmployeeuidn());
		Exam exam = new Exam();
		exam.setDermatologist(derm);
		exam.setPatient(patient);
		exam.setAppointmentStatus(AppointmentStatus.OCCUPIED);
		exam.setDateRange(range);
		return examRepository.save(exam);
	}
}
