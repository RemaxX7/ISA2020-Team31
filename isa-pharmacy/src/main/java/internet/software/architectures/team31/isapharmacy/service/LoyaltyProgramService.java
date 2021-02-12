package internet.software.architectures.team31.isapharmacy.service;

import internet.software.architectures.team31.isapharmacy.domain.patient.LoyaltyProgramDiscount;

public interface LoyaltyProgramService {
	
	LoyaltyProgramDiscount updateLoyaltyProgramDiscount(Long loyaltyProgramDiscountId, Double percentage);

}
