package internet.software.architectures.team31.isapharmacy.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

	Collection<Exam> findAllByPatientId(Long id);
	Collection<Exam> findAllByDermatologistId(Long id);
	Collection<Exam> findAllByAppointmentStatus(AppointmentStatus status);
	Page<Exam> findAllByAppointmentStatus(AppointmentStatus status, Pageable pageable);
	Collection<Exam> findAllByPatientIdAndAppointmentStatus(Long patientId, AppointmentStatus status);
	Page<Exam> findAllByPatientIdAndAppointmentStatus(Long patientId, AppointmentStatus status, Pageable pageable);
	boolean existsByPatientIdAndDermatologistIdAndAppointmentStatus(Long patientId, Long dermatologistId, AppointmentStatus status);
	
	@Query("SELECT m FROM Appointments m ORDER BY m.dateRange ASC")
	List<Exam> findAllByOrderByDateRangeAsc();
}
