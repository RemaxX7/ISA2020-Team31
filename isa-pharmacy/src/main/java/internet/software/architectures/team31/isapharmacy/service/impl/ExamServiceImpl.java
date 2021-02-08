package internet.software.architectures.team31.isapharmacy.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentMedicineItem;
import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.domain.users.Dermatologist;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentFinalizationDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentScheduleDTO;
import internet.software.architectures.team31.isapharmacy.dto.ExamCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;
import internet.software.architectures.team31.isapharmacy.repository.ExamRepository;
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
	private MedicineServiceImpl medicineService;

	@Override
	public Exam save(ExamCreateDTO dto) {
		Exam exam = new Exam(dto);
		exam.setPharmacy(pharmacyService.findById(dto.getPharmacyId()));
		exam.setDermatologist((Dermatologist) userService.findById(dto.getDermatologistId()));
		return examRepository.save(exam);
	}
	
	@Override
	public Exam schedule(AppointmentScheduleDTO dto) throws PenaltyException, AppointmentNotFreeException {
		Patient patient = (Patient) userService.findById(dto.getPatientId());
		
		if(patient.getPenalty() >= 3) {
			throw new PenaltyException("Cannot schedule an exam. Maximum number of penalties exceeded.");
		}
		
		Exam exam = findById(dto.getAppointmentId());
		if(exam.getAppointmentStatus() != AppointmentStatus.FREE) {
			throw new AppointmentNotFreeException("Appointment term is not available.");
		}
		
		exam.setPatient(patient);
		return examRepository.save(exam);
	}

	@Override
	public Exam cancel(Long id) throws CancelAppointmentException {
		Exam exam = findById(id);
		LocalDateTime currentDateTime = LocalDateTime.now();
		if(!currentDateTime.plusDays(1).isBefore(exam.getDateRange().getStartDateTime())) {
			throw new CancelAppointmentException("Exam cannot be cancelled 24 hours before start.");
		}
		exam.setPatient(null);
		exam.setAppointmentStatus(AppointmentStatus.FREE);
		return examRepository.save(exam);
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
	public Exam findById(Long id) {
		return examRepository.findById(id).orElse(null);
	}

	@Override
	public boolean hasPatientVisitedDermatologist(Long patientId, Long dermatologistId) {
		return examRepository.findOneByPatientIdAndDermatologistIdAndAppointmentStatus(patientId, dermatologistId, AppointmentStatus.FINISHED) != null;
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
}
