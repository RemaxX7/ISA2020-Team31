package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;

public interface CounselingRepository extends JpaRepository<Counseling, Long> {

	Collection<Counseling> findAllByPatientId(Long id);
	Collection<Counseling> findAllByPharmacistId(Long id);
	Collection<Counseling> findAllByAppointmentStatus(AppointmentStatus status);
	Collection<Counseling> findAllByPatientIdAndAppointmentStatus(Long patientId, AppointmentStatus status);
	Collection<Counseling> findAllByPharmacistIdAndAppointmentStatus(Long pharmacistId, AppointmentStatus status);
	Page<Counseling> findAllByPatientIdAndAppointmentStatus(Long patientId, AppointmentStatus status, Pageable pageable);
	boolean existsByPatientIdAndPharmacistIdAndAppointmentStatus(Long patientId, Long pharmacistId, AppointmentStatus status);
	
	@Query("SELECT m FROM Appointments m ORDER BY m.dateRange ASC")
	List<Counseling> findAllByOrderByDateRangeAsc();
}
