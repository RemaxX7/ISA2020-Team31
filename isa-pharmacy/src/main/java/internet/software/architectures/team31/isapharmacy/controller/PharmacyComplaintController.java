package internet.software.architectures.team31.isapharmacy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.patient.ComplaintReply;
import internet.software.architectures.team31.isapharmacy.domain.patient.PharmacyComplaint;
import internet.software.architectures.team31.isapharmacy.dto.ComplaintReplyCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.PharmacyComplaintCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AlreadyRepliedException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidComplaintException;
import internet.software.architectures.team31.isapharmacy.service.PharmacyComplaintService;

@RestController
@RequestMapping(value = "api/complaints/pharmacy")
public class PharmacyComplaintController {

	@Autowired
	private PharmacyComplaintService pharmacyComplaintService;

	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "/save")
	public ResponseEntity<PharmacyComplaint> save(@RequestBody PharmacyComplaintCreateDTO dto) throws InvalidComplaintException {
		return new ResponseEntity<>(pharmacyComplaintService.save(dto), HttpStatus.CREATED);
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
	
	@PutMapping(value = "/reply")
	public ResponseEntity<ComplaintReply> reply(@RequestBody ComplaintReplyCreateDTO dto) throws AlreadyRepliedException{
		return new ResponseEntity<>(pharmacyComplaintService.reply(dto), HttpStatus.OK);
	}
}
