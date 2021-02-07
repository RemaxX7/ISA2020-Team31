package internet.software.architectures.team31.isapharmacy.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.users.Dermatologist;
import internet.software.architectures.team31.isapharmacy.service.DermatologistService;

@RestController
@RequestMapping(value = "api/dermatologist")
public class DermatologistController {
	
	@Autowired
	private DermatologistService dermatologistService;
	
	@GetMapping("/all/{pharmacyId}")
	public ResponseEntity<List<Dermatologist>> findAllByPharmacy(@PathVariable Long pharmacyId) {
		return new ResponseEntity<List<Dermatologist>>(this.dermatologistService.findAllByPharmacy(pharmacyId), HttpStatus.OK);
	}
}
