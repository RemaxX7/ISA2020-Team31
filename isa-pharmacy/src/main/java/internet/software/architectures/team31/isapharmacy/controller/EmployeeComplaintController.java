package internet.software.architectures.team31.isapharmacy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.patient.EmployeeComplaint;
import internet.software.architectures.team31.isapharmacy.domain.users.Employee;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.dto.EmployeeComplaintCreateDTO;
import internet.software.architectures.team31.isapharmacy.service.EmployeeComplaintService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@RestController
@RequestMapping(value = "api/complaints/employee")
public class EmployeeComplaintController {

	@Autowired
	private EmployeeComplaintService employeeComplaintService;
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "/save")
	public ResponseEntity<EmployeeComplaint> save(@RequestBody EmployeeComplaintCreateDTO dto) {
		
		EmployeeComplaint employeeComplaint = new EmployeeComplaint(dto);
		employeeComplaint.setPatient((Patient) userService.findById(dto.getPatientId()));
		employeeComplaint.setEmployee((Employee) userService.findById(dto.getEmployeeId()));
		
		return new ResponseEntity<>(employeeComplaintService.save(employeeComplaint), HttpStatus.OK);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<Collection<EmployeeComplaint>> findAll() {
		return new ResponseEntity<>(employeeComplaintService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/patient/{id}")
	public ResponseEntity<Collection<EmployeeComplaint>> findAllByPatient(@PathVariable Long id) {
		return new ResponseEntity<>(employeeComplaintService.findAllByPatientId(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EmployeeComplaint> findById(@PathVariable Long id) {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<>(employeeComplaintService.findById(id), HttpStatus.OK);
	}
}
