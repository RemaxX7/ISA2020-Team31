package internet.software.architectures.team31.isapharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pricelist;

public interface PricelistRepository extends JpaRepository<Pricelist, Long> {

}
