package internet.software.architectures.team31.isapharmacy.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentScheduleDTO;
import internet.software.architectures.team31.isapharmacy.dto.CounselingCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;
import internet.software.architectures.team31.isapharmacy.repository.CounselingRepository;
import internet.software.architectures.team31.isapharmacy.service.CounselingService;
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

	@Override
	public Counseling save(CounselingCreateDTO dto) {
		Counseling counseling = new Counseling(dto);
		counseling.setPharmacy(pharmacyService.findById(dto.getPharmacyId()));
		counseling.setPharmacist((Pharmacist) userService.findById(dto.getPharmacistId()));
		return counselingRepository.save(counseling);
	}

	@Override
	public Counseling schedule(AppointmentScheduleDTO dto) throws PenaltyException, AppointmentNotFreeException {
		Patient patient = (Patient) userService.findById(dto.getPatientId());
		if(patient.getPenalty() >= 3) {
			throw new PenaltyException("Cannot schedule counseling. Maximum number of penalties exceeded.");
		}
		
		Counseling counseling = findById(dto.getAppointmentId());
		if(counseling.getAppointmentStatus() != AppointmentStatus.FREE) {
			throw new AppointmentNotFreeException("Appointment term is not available.");
		}
		counseling.setPatient((Patient) userService.findById(dto.getPatientId()));
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
}
