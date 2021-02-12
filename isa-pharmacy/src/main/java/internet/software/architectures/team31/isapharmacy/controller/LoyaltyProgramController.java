package internet.software.architectures.team31.isapharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.patient.LoyaltyProgramDiscount;
import internet.software.architectures.team31.isapharmacy.service.LoyaltyProgramService;

@RestController
@RequestMapping(value = "auth/loyaltyprogram")
public class LoyaltyProgramController {

	@Autowired
	private LoyaltyProgramService loyaltyProgramService;
	
	@PostMapping(value = "/update/percentage/{loyaltyProgramDiscountId}/{percentage}")
	public ResponseEntity<LoyaltyProgramDiscount> save(@PathVariable Long loyaltyProgramDiscountId , @PathVariable Double percentage) {
		return new ResponseEntity<>(loyaltyProgramService.updateLoyaltyProgramDiscount(loyaltyProgramDiscountId, percentage), HttpStatus.OK);
	}
}
