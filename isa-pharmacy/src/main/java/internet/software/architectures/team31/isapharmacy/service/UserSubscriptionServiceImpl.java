package internet.software.architectures.team31.isapharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.users.UserSubscription;
import internet.software.architectures.team31.isapharmacy.repository.UserSubscriptionRepository;

@Service
public class UserSubscriptionServiceImpl implements UserSubscriptionService {

	@Autowired
	private UserSubscriptionRepository userSubscriptionRepository;
	
	@Override
	public UserSubscription save(UserSubscription userSubsctiption) {
		return userSubscriptionRepository.save(userSubsctiption);
	}
	
	@Override
	public UserSubscription delete(Long userId, Long pharmacyId) {
		UserSubscription userSubscription = userSubscriptionRepository.findByUserIdAndPharmacyId(userId, pharmacyId);
		userSubscriptionRepository.deleteById(userSubscription.getId());
		
		return userSubscription;
	}
}
