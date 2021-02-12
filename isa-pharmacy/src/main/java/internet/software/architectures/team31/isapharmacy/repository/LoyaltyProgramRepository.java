package internet.software.architectures.team31.isapharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.patient.LoyaltyProgramDiscount;

public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgramDiscount, Long> {

}
