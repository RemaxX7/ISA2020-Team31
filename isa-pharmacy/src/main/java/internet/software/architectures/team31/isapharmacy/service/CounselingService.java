package internet.software.architectures.team31.isapharmacy.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.domain.schedule.Shift;
import internet.software.architectures.team31.isapharmacy.dto.AdditionalExamSchedulingDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentFinalizationDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentViewDTO;
import internet.software.architectures.team31.isapharmacy.dto.CounselingCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.UserViewDTO;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.CounselingAlreadyScheduledException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;

public interface CounselingService {

	Counseling save(CounselingCreateDTO dto) throws PenaltyException, CounselingAlreadyScheduledException;
	AppointmentViewDTO schedule(Long id) throws PenaltyException, AppointmentNotFreeException, CounselingAlreadyScheduledException;
	Boolean cancel(Long id) throws CancelAppointmentException;
	Collection<Counseling> findAll();
	Collection<Counseling> findAllActive();
	Collection<Counseling> findAllByPatientId(Long id);
	Collection<Counseling> findAllByPharmacistId(Long id);
	Collection<Counseling> findAllByAppointmentStatus(AppointmentStatus status);
	Collection<Counseling> findAllByPatientIdAndAppointmentStatus(Long patientId, AppointmentStatus status);
	Collection<UserViewDTO> findAvailablePharmacists(LocalDateTime dateTime, Long pharmacyId);
	Page<AppointmentViewDTO> findAllByPatientIdAndAppointmentStatus(AppointmentStatus status, Pageable pageable);
	Counseling findById(Long id);
	boolean hasPatientVisitedPharmacist(Long patientId, Long pharmacistId);
	boolean hasPatientAlreadyScheduledCounseling(Long patientId, Long pharmacistId);
	boolean areThereAvailablePharmacists(Shift shift, LocalDateTime dateTime);
	Counseling finalizeExam(AppointmentFinalizationDTO dto,String quant);
	Counseling scheduleAdditionalConsultation(AdditionalExamSchedulingDTO dto);
	List<String> findTerminsByUidnsPharm(String patuidn,String empuidn);
	List<Counseling>findCounsForPharm(String uidn,String days);
	List<Exam>findExamsForDerm(String uidn,String days);
	Counseling updatePoints(Long counselingId, Integer points);
}
