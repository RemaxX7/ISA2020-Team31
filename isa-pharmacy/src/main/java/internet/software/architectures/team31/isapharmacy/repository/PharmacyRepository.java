package internet.software.architectures.team31.isapharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

}
