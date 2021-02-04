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

import internet.software.architectures.team31.isapharmacy.domain.patient.PharmacyComplaint;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.dto.PharmacyComplaintCreateDTO;
import internet.software.architectures.team31.isapharmacy.service.PharmacyComplaintService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@RestController
@RequestMapping(value = "api/complaints/pharmacy")
public class PharmacyComplaintController {

	@Autowired
	private PharmacyComplaintService pharmacyComplaintService;
	@Autowired
	private UserService userService;
	@Autowired
	private PharmacyService pharmacyService;
	
	@PostMapping(value = "/save")
	public ResponseEntity<PharmacyComplaint> save(@RequestBody PharmacyComplaintCreateDTO dto) {
		
		PharmacyComplaint pharmacyComplaint = new PharmacyComplaint(dto);
		pharmacyComplaint.setPatient((Patient) userService.findById(dto.getPatientId()));
		pharmacyComplaint.setPharmacy(pharmacyService.findById(dto.getPharmacyId()));
		
		return new ResponseEntity<>(pharmacyComplaintService.save(pharmacyComplaint), HttpStatus.OK);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<Collection<PharmacyComplaint>> findAll() {
		return new ResponseEntity<>(pharmacyComplaintService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/patient/{id}")
	public ResponseEntity<Collection<PharmacyComplaint>> findAllByPatient(@PathVariable Long id) {
		return new ResponseEntity<>(pharmacyComplaintService.findAllByPatientId(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PharmacyComplaint> findById(@PathVariable Long id) {
		return new ResponseEntity<>(pharmacyComplaintService.findById(id), HttpStatus.OK);
	}
}
