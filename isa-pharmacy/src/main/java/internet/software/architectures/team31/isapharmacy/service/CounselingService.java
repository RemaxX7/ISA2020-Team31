package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.dto.CounselingCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentFinalizationDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentScheduleDTO;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;

public interface CounselingService {

	Counseling save(CounselingCreateDTO dto);
	Counseling schedule(AppointmentScheduleDTO dto) throws PenaltyException, AppointmentNotFreeException;
	Counseling cancel(Long id) throws CancelAppointmentException;
	Collection<Counseling> findAll();
	Collection<Counseling> findAllByPatientId(Long id);
	Collection<Counseling> findAllByPharmacistId(Long id);
	Collection<Counseling> findAllByAppointmentStatus(AppointmentStatus status);
	Collection<Counseling> findAllByPatientIdAndAppointmentStatus(Long patientId, AppointmentStatus status);
	Counseling findById(Long id);
	boolean hasPatientVisitedPharmacist(Long patientId, Long pharmacistId);
	Counseling finalizeExam(AppointmentFinalizationDTO dto);
}
