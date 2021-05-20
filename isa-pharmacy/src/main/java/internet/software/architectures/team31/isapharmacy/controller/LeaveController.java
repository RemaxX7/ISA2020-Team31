package internet.software.architectures.team31.isapharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@PostMapping(value = "/createLeaveRequest/{employeeId}")
	public ResponseEntity<ScheduleLeave> findAllActive(@PathVariable String employeeId,@RequestBody String dateRange) {
		return new ResponseEntity<>(leaveService.save(employeeId,dateRange), HttpStatus.OK);
	}
}
