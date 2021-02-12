package internet.software.architectures.team31.isapharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.users.UserSubscription;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {

	UserSubscription findByUserIdAndPharmacyId(Long userId, Long pharmacyId);
}
