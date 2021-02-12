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
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;
import internet.software.architectures.team31.isapharmacy.dto.AdditionalExamSchedulingDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentFinalizationDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentViewDTO;
import internet.software.architectures.team31.isapharmacy.dto.CounselingCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.CounselingAlreadyScheduledException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;
import internet.software.architectures.team31.isapharmacy.repository.CounselingRepository;
import internet.software.architectures.team31.isapharmacy.service.CounselingService;
import internet.software.architectures.team31.isapharmacy.service.EmailService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@Service
public class CounselingServiceImpl implements CounselingService {

	@Autowired
	private CounselingRepository counselingRepository;
	@Autowired
	private PharmacyService pharmacyService;
	@Autowired
	private UserService userService;
	@Autowired
	private PatientServiceImpl patientService;
	@Autowired
	private MedicineServiceImpl medicineService;
	
	private EmailService emailService;

	@Override
	public Counseling save(CounselingCreateDTO dto) {
		Counseling counseling = new Counseling(dto);
		counseling.setPharmacy(pharmacyService.findById(dto.getPharmacyId()));
		counseling.setPharmacist((Pharmacist) userService.findById(dto.getPharmacistId()));
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
	public AppointmentViewDTO cancel(Long id) throws CancelAppointmentException {
		Counseling counseling = findById(id);
		LocalDateTime currentDateTime = LocalDateTime.now();
		if(!currentDateTime.plusDays(1).isBefore(counseling.getDateRange().getStartDateTime())) {
			throw new CancelAppointmentException("This counseling cannot be cancelled.");
		}
		counseling.setPatient(null);
		counseling.setAppointmentStatus(AppointmentStatus.FREE);
		return new AppointmentViewDTO(counselingRepository.save(counseling));
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Patient patient = patientService.findByUidn(dto.getUidn());
		LocalDateTime date = LocalDateTime.parse(dto.getDate(),formatter);
		DateRange range = new DateRange();
		range.setStartDateTime(date);
		range.setEndDateTime(date.plusMinutes(30));
		Pharmacist pharm = (Pharmacist) userService.findByUidn(dto.getEmployeeuidn());
		Counseling counseling = new Counseling();
		counseling.setPharmacist(pharm);
		counseling.setPatient(patient);
		counseling.setAppointmentStatus(AppointmentStatus.OCCUPIED);
		counseling.setDateRange(range);
		return counselingRepository.save(counseling);
	}
}
