package internet.software.architectures.team31.isapharmacy.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.dto.AdditionalExamSchedulingDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentFinalizationDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentViewDTO;
import internet.software.architectures.team31.isapharmacy.dto.ExamCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidInputException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;
import internet.software.architectures.team31.isapharmacy.exception.ShiftNotFreeEception;
import internet.software.architectures.team31.isapharmacy.exception.UserNotTypePatientException;

public interface ExamService {

	Exam save(ExamCreateDTO dto);
	AppointmentViewDTO schedule(Long id) throws PenaltyException, AppointmentNotFreeException;
	AppointmentViewDTO cancel(Long id) throws CancelAppointmentException;
	Collection<Exam> findAll();
	Collection<Exam> findAllActive(String uidn);
	Collection<Exam> findAllByPatientId(Long id);
	Collection<Exam> findAllByDermatologistId(Long id);
	Collection<Exam> findAllByAppointmentStatus(AppointmentStatus status);
	Page<AppointmentViewDTO> findAllByAppointmentStatus(AppointmentStatus status, Pageable pageable);
	Collection<Exam> findAllByPatientIdAndAppointmentStatus(Long patientId, AppointmentStatus status);
	Page<AppointmentViewDTO> findAllByPatientIdAndAppointmentStatus(AppointmentStatus status, Pageable pageable);
	Exam findById(Long id);
	boolean hasPatientVisitedDermatologist(Long patientId, Long dermatologistId);
	Exam finalizeExam(AppointmentFinalizationDTO dto,String examid,String quant) throws InvalidInputException;
	Exam scheduleAdditionalExam(AdditionalExamSchedulingDTO dto,Boolean fromExam) throws ShiftNotFreeEception, UserNotTypePatientException;
	List<String> findTerminsByUidns(String patuidn,String empuidn);
	Exam updatePoints(Long examId, Integer points);
	List<Patient> findCheckedPatientsDermatologist(String uidn);
	List<Exam> findAllByOrderByStartDateTimeAsc();
	List<Exam> findExamsForDerm(String uidn, String days);
}
