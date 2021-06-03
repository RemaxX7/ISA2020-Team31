package internet.software.architectures.team31.isapharmacy.service;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.ScheduleLeave;

public interface LeaveService {
	ScheduleLeave save(String employeeId,String dateRange);
}
