package internet.software.architectures.team31.isapharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;

@RestController
@RequestMapping(value = "api/pharmacy")
public class PharmacyController {
	
	@Autowired
	private PharmacyService pharmacyService;

	@GetMapping("/{pharmacyId}")
	public Pharmacy loadById(@PathVariable Long pharmacyId) {
		return this.pharmacyService.findById(pharmacyId);
	}
}
