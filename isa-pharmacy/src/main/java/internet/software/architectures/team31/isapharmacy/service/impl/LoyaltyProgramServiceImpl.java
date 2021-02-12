package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.patient.LoyaltyProgramDiscount;
import internet.software.architectures.team31.isapharmacy.repository.LoyaltyProgramRepository;
import internet.software.architectures.team31.isapharmacy.service.LoyaltyProgramService;

@Service
public class LoyaltyProgramServiceImpl implements LoyaltyProgramService {

	@Autowired
	private LoyaltyProgramRepository loyaltyProgramRepository;
	
	@Override
	public LoyaltyProgramDiscount updateLoyaltyProgramDiscount(Long loyaltyProgramDiscountId, Double percentage) {
		Optional<LoyaltyProgramDiscount> loyaltyProgramDiscount = loyaltyProgramRepository.findById(loyaltyProgramDiscountId);
		loyaltyProgramDiscount.get().setPercentage(percentage);
		
		return loyaltyProgramRepository.save(loyaltyProgramDiscount.get());		
	}

}
