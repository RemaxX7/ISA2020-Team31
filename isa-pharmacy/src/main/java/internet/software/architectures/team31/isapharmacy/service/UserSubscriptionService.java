package internet.software.architectures.team31.isapharmacy.service;

import internet.software.architectures.team31.isapharmacy.domain.users.UserSubscription;

public interface UserSubscriptionService {
	
	UserSubscription save(UserSubscription UserSubsctiption);
	UserSubscription delete(Long userId, Long pharmacyId);

}
