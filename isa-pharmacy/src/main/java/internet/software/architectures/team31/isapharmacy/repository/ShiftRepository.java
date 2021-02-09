package internet.software.architectures.team31.isapharmacy.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import internet.software.architectures.team31.isapharmacy.domain.schedule.Shift;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;

public interface ShiftRepository extends JpaRepository<Shift,Long> {
	
	List<Shift> findAllByEmployeeId(Long id);
	List<Shift> findAllByPharmacyId(Long id);
	
	@Query(value = "SELECT * FROM"
			+ " shifts s  WHERE s.pharmacy_id=:pharmacyId AND (s.start_date_time BETWEEN :start AND :end OR s.end_date_time BETWEEN :start AND :end) ", nativeQuery = true )
	List<Shift> findAllByIntervalAndPharmacyId(@Param("start")LocalDateTime startDateTime,
												@Param("end")LocalDateTime endDateTime,
												@Param("pharmacyId")Long pharmachyId);
	
	@Query(value = "SELECT * FROM"
			+ " shifts s  WHERE s.employee_id=:employeeId AND (s.start_date_time BETWEEN :start AND :end OR s.end_date_time BETWEEN :start AND :end) ", nativeQuery = true )
	List<Shift> findAllByIntervalAndEmployeeId(@Param("start")LocalDateTime startDateTime,
												@Param("end")LocalDateTime endDateTime,
												@Param("employeeId")Long employeeId);
	
}
