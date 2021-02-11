package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.dto.AdditionalExamSchedulingDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentFinalizationDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentViewDTO;
import internet.software.architectures.team31.isapharmacy.dto.CounselingCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.CounselingAlreadyScheduledException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;

public interface CounselingService {

	Counseling save(CounselingCreateDTO dto);
	AppointmentViewDTO schedule(Long id) throws PenaltyException, AppointmentNotFreeException, CounselingAlreadyScheduledException;
	AppointmentViewDTO cancel(Long id) throws CancelAppointmentException;
	Collection<Counseling> findAll();
	Collection<Counseling> findAllByPatientId(Long id);
	Collection<Counseling> findAllByPharmacistId(Long id);
	Collection<Counseling> findAllByAppointmentStatus(AppointmentStatus status);
	Collection<Counseling> findAllByPatientIdAndAppointmentStatus(Long patientId, AppointmentStatus status);
	Page<AppointmentViewDTO> findAllByPatientIdAndAppointmentStatus(AppointmentStatus status, Pageable pageable);
	Counseling findById(Long id);
	boolean hasPatientVisitedPharmacist(Long patientId, Long pharmacistId);
	boolean hasPatientAlreadyScheduledCounseling(Long patientId, Long pharmacistId);
	Counseling finalizeExam(AppointmentFinalizationDTO dto);
	Counseling scheduleAdditionalConsultation(AdditionalExamSchedulingDTO dto);
}
