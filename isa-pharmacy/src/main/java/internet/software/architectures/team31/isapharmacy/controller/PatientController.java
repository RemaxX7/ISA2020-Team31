package internet.software.architectures.team31.isapharmacy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.dto.AllergiesDTO;
import internet.software.architectures.team31.isapharmacy.dto.PasswordUpdateDTO;
import internet.software.architectures.team31.isapharmacy.dto.PatientProfileDTO;
import internet.software.architectures.team31.isapharmacy.exception.PasswordControlException;
import internet.software.architectures.team31.isapharmacy.service.PatientService;

@RestController
@RequestMapping(value = "api/patients")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@GetMapping("/all")
	public ResponseEntity<Collection<Patient>> findAll() {
		return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/profile")
	public ResponseEntity<PatientProfileDTO> getProfile() {
		return new ResponseEntity<>(patientService.getPatientProfile(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "/update/profile")
	public ResponseEntity<PatientProfileDTO> updateProfile(@RequestBody PatientProfileDTO dto) {
		return new ResponseEntity<>(patientService.updatePatientProfile(dto), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/allergies")
	public ResponseEntity<AllergiesDTO> getAllergies() {
		return new ResponseEntity<>(patientService.getPatientAllergies(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "/update/allergies")
	public ResponseEntity<AllergiesDTO> updateAllergies(@RequestBody AllergiesDTO dto) {
		return new ResponseEntity<>(patientService.updatePatientAllergies(dto), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "update/password")
	public ResponseEntity<Boolean> updatePassword(@RequestBody PasswordUpdateDTO dto) throws PasswordControlException {
		return new ResponseEntity<>(patientService.updatePatientPassword(dto), HttpStatus.OK);
	}
}
