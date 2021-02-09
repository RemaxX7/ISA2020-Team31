package internet.software.architectures.team31.isapharmacy.service;

import java.time.LocalDateTime;
import java.util.List;

import internet.software.architectures.team31.isapharmacy.domain.schedule.Shift;
import internet.software.architectures.team31.isapharmacy.dto.ShiftCreateDTO;

public interface ShiftService {
	List<Shift> findAllByEmployeeId(Long id);
	List<Shift> findAllByPharmacyId(Long id);
	Shift save(ShiftCreateDTO shift);
	List<Shift> findAllByIntervalAndPharmacyId(LocalDateTime startDateTime,LocalDateTime endDateTime,Long pharmachyId);
	List<Shift> findAllByIntervalAndEmployeeId(LocalDateTime startDateTime,LocalDateTime endDateTime,Long employeeId);
}
