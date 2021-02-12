package internet.software.architectures.team31.isapharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.UserSubscription;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;
import internet.software.architectures.team31.isapharmacy.service.UserService;
import internet.software.architectures.team31.isapharmacy.service.UserSubscriptionService;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@Autowired
	private UserSubscriptionService userSubscriptionService;

	@PostMapping("/action/subscribe/{userId}/{pharmacyId}")
	public Patient subscribeToPharmacy(@PathVariable Long userId, @PathVariable Long pharmacyId) {
		Patient patient = (Patient) userService.findOne(userId);
		Pharmacy pharmacy = pharmacyService.findById(pharmacyId);
		
		if (patient != null && pharmacy != null) {
			userSubscriptionService.save(new UserSubscription(userId, pharmacyId));
		}
		
		return patient;
	}
	
	@PostMapping("/action/cancel/{userId}/{pharmacyId}")
	public Patient cancelSubscription(@PathVariable Long userId, @PathVariable Long pharmacyId) {
		Patient patient = (Patient) userService.findOne(userId);
		Pharmacy pharmacy = pharmacyService.findById(pharmacyId);
		
		if (patient != null && pharmacy != null) {
			userSubscriptionService.delete(userId, pharmacyId);
		}
		
		return patient;
	}
}
