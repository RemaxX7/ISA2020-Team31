package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

	Collection<Exam> findAllByPatientId(Long id);
	Collection<Exam> findAllByDermatologistId(Long id);
	Collection<Exam> findAllByAppointmentStatus(AppointmentStatus status);
	Collection<Counseling> findAllByPatientIdAndAppointmentStatus(Long patientId, AppointmentStatus status);
	Exam findOneByPatientIdAndDermatologistIdAndAppointmentStatus(Long patientId, Long dermatologistId, AppointmentStatus status);
}
