package internet.software.architectures.team31.isapharmacy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.users.User;
import internet.software.architectures.team31.isapharmacy.dto.EmployeeProfileEditDTO;
import internet.software.architectures.team31.isapharmacy.dto.PasswordChangeDTO;
import internet.software.architectures.team31.isapharmacy.service.UserDetailsServiceImpl;
import internet.software.architectures.team31.isapharmacy.service.UserService;
import internet.software.architectures.team31.isapharmacy.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "auth/search/employee")
public class EmployeeController {
	
	@Autowired
	private UserDetailsServiceImpl userService;
	@Autowired
	private UserService employedService;
	@GetMapping("/all")
	public ResponseEntity<Collection<User>> findAll() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}
	@GetMapping("/getbyid/{uidn}")
	public ResponseEntity<User> findByID(@PathVariable String uidn) {
		return new ResponseEntity<>(userService.findByUidn(uidn), HttpStatus.OK);
	}
	@PostMapping(value = "/editprofile")
	public ResponseEntity<User> editUserProfile(@RequestBody EmployeeProfileEditDTO dto){
		return new ResponseEntity<>(employedService.employeeEditProfile(dto),HttpStatus.OK);
	}
	@PostMapping(value = "/editpassword")
	public ResponseEntity<User> editUserPassword(@RequestBody PasswordChangeDTO dto){
		return new ResponseEntity<>(employedService.employeeEditPassword(dto),HttpStatus.OK);
	}
}
