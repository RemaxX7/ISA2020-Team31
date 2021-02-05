package internet.software.architectures.team31.isapharmacy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.patient.ComplaintReply;
import internet.software.architectures.team31.isapharmacy.domain.patient.EmployeeComplaint;
import internet.software.architectures.team31.isapharmacy.dto.ComplaintReplyCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.EmployeeComplaintCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AlreadyRepliedException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidComplaintException;
import internet.software.architectures.team31.isapharmacy.service.EmployeeComplaintService;

@RestController
@RequestMapping(value = "api/complaints/employee")
public class EmployeeComplaintController {

	@Autowired
	private EmployeeComplaintService employeeComplaintService;
	
	@PostMapping(value = "/save")
	public ResponseEntity<EmployeeComplaint> save(@RequestBody EmployeeComplaintCreateDTO dto) throws InvalidComplaintException {
		return new ResponseEntity<>(employeeComplaintService.save(dto), HttpStatus.CREATED);
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
	
	@PutMapping(value = "/reply")
	public ResponseEntity<ComplaintReply> reply(@RequestBody ComplaintReplyCreateDTO dto) throws AlreadyRepliedException{
		return new ResponseEntity<>(employeeComplaintService.reply(dto), HttpStatus.OK);
	}
}
