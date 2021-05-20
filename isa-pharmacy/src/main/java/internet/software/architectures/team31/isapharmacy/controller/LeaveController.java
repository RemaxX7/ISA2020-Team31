package internet.software.architectures.team31.isapharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.ScheduleLeave;
import internet.software.architectures.team31.isapharmacy.service.LeaveService;

@RestController
@RequestMapping(value = "api/leave")
public class LeaveController {
	@Autowired
	private LeaveService leaveService;
	
	@PreAuthorize("hasRole('PHARMACIST') or hasRole('DERMATOLOGIST')")
	@GetMapping(value = "/createLeaveRequest")
	public ResponseEntity<ScheduleLeave> findAllActive(@PathVariable String employeeId,@PathVariable String startDate,@PathVariable String endDate) {
		return new ResponseEntity<>(leaveService.save(employeeId,startDate,endDate), HttpStatus.OK);
	}
}
