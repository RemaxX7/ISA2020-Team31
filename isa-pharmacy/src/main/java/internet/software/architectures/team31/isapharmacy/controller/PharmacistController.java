package internet.software.architectures.team31.isapharmacy.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;
import internet.software.architectures.team31.isapharmacy.service.PharmacistService;


@RestController
@RequestMapping(value = "api/pharmacist")
public class PharmacistController {
	
	@Autowired
	private PharmacistService pharmacistService;
	
	@GetMapping("/all/{pharmacyId}")
	public ResponseEntity<Collection<Pharmacist>> findAllByPharmacyId(@PathVariable Long pharmacyId) {
		return new ResponseEntity<Collection<Pharmacist>>(this.pharmacistService.findAllByPharmacyId(pharmacyId), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Pharmacist>> findAll(){
		return new ResponseEntity<List<Pharmacist>>(this.pharmacistService.findAll(), HttpStatus.OK);
	}
}
