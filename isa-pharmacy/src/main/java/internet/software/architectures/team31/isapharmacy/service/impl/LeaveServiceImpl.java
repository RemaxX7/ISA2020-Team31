package internet.software.architectures.team31.isapharmacy.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.LeaveStatus;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.ScheduleLeave;
import internet.software.architectures.team31.isapharmacy.domain.users.Employee;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;
import internet.software.architectures.team31.isapharmacy.repository.LeaveRepository;
import internet.software.architectures.team31.isapharmacy.service.LeaveService;
@Service
public class LeaveServiceImpl implements LeaveService{
	@Autowired
	private LeaveServiceImpl leaveService;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private LeaveRepository leaveRepository;
	
	@Override
	public ScheduleLeave save(String employeeId, String startDate, String endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime leaveDateStart = LocalDateTime.parse(startDate,formatter);
		LocalDateTime leaveDateEnd = LocalDateTime.parse(endDate,formatter);
		DateRange range = new DateRange();
		range.setStartDateTime(leaveDateStart);
		range.setEndDateTime(leaveDateEnd);
		range.setStartDateTime(LocalDateTime.parse(startDate));
		Employee employee = (Employee) userService.findByUidn(employeeId);
		ScheduleLeave leave = new ScheduleLeave();
		leave.setEmployee(employee);
		leave.setStatus(LeaveStatus.CREATED);
		leave.setDateRange(range);
		return leaveRepository.save(leave);
	}

}
