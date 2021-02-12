package internet.software.architectures.team31.isapharmacy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.service.impl.PatientServiceImpl;

@RestController
@RequestMapping(value = "api/search/patients")
public class PatientController {
	@Autowired
	private PatientServiceImpl patientService;
	
	@GetMapping("/all")
	public ResponseEntity<Collection<Patient>> findAll() {
		return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
	}
}
