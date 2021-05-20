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
	private UserServiceImpl userService;
	@Autowired
	private LeaveRepository leaveRepository;
	
	@Override
	public ScheduleLeave save(String employeeId, String dateRange) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String[] date = dateRange.split("-");
		String start = date[0].substring(10)+"-"+date[1]+"-"+date[2].substring(0,2)+" "+date[2].substring(3,5)+":"+date[2].substring(6,8);
		String end = date[2].substring(25,29)+"-"+date[3]+"-"+date[4].substring(0,2)+" "+date[4].substring(3,5)+":"+date[4].substring(6,8);
		DateRange range = new DateRange();
		range.setStartDateTime(LocalDateTime.parse(start,formatter));
		range.setEndDateTime(LocalDateTime.parse(end,formatter));
		Employee employee = (Employee) userService.findByUidn(employeeId);
		ScheduleLeave leave = new ScheduleLeave();
		leave.setEmployee(employee);
		leave.setStatus(LeaveStatus.CREATED);
		leave.setDateRange(range);
		return leaveRepository.save(leave);
	}

}
