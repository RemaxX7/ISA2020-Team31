package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.dto.ExamCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentFinalizationDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentScheduleDTO;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;

public interface ExamService {

	Exam save(ExamCreateDTO dto);
	Exam schedule(AppointmentScheduleDTO dto) throws PenaltyException, AppointmentNotFreeException;
	Exam cancel(Long id) throws CancelAppointmentException;
	Collection<Exam> findAll();
	Collection<Exam> findAllByPatientId(Long id);
	Collection<Exam> findAllByDermatologistId(Long id);
	Collection<Exam> findAllByAppointmentStatus(AppointmentStatus status);
	Exam findById(Long id);
	boolean hasPatientVisitedDermatologist(Long patientId, Long dermatologistId);
	Exam finalizeExam(AppointmentFinalizationDTO dto);
}
