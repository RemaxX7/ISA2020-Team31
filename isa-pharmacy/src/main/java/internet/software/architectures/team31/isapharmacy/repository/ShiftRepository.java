package internet.software.architectures.team31.isapharmacy.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import internet.software.architectures.team31.isapharmacy.domain.schedule.Shift;

public interface ShiftRepository extends JpaRepository<Shift,Long> {
	
	List<Shift> findAllByEmployeeId(Long id);
	List<Shift> findAllByEmployeeUidn(String uidn);
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
												@Param("employeeId")Long id);
	
	@Query(value = "SELECT * FROM"
			+ " shifts s  WHERE :date BETWEEN s.start_date_time AND s.end_date_time", nativeQuery = true)
	Collection<Shift> findAllByDate(@Param("date") LocalDateTime dateTime);
	
	@Query(value = "SELECT * FROM"
			+ " shifts s  WHERE :date BETWEEN s.start_date_time AND s.end_date_time AND s.pharmacy_id = :pharmacyId", nativeQuery = true)
	Collection<Shift> findAllByDateAndPharmacyId(@Param("date") LocalDateTime dateTime, @Param("pharmacyId") Long pharmacyId);
}
