package internet.software.architectures.team31.isapharmacy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.users.User;
import internet.software.architectures.team31.isapharmacy.service.UserDetailsServiceImpl;

@RestController
@RequestMapping(value = "auth/search/employee")
public class EmployeeController {
	
	@Autowired
	private UserDetailsServiceImpl userService;
	
	@GetMapping("/all")
	public ResponseEntity<Collection<User>> findAll() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}
	@GetMapping("/getbyid/{uidn}")
	public ResponseEntity<User> findByID(@PathVariable String uidn) {
		return new ResponseEntity<>(userService.findByUidn(uidn), HttpStatus.OK);
	}
	/*@GetMapping("/getAppointments")
	public ResponseEntity<Collection<Appointment>> findAllAppointments(){
		return new ResponseEntity<Collection<Appointment>>(appointmentservice.getAll(),HttpStatus.OK);
	}*/
}
