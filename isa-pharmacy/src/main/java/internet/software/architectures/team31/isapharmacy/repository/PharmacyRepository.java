package internet.software.architectures.team31.isapharmacy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

	@Query("SELECT p FROM Pharmacy p WHERE LOWER(p.name) LIKE LOWER(concat('%', ?1,'%')) OR "
			+ "LOWER(p.address.street) LIKE LOWER(concat('%', ?1,'%')) OR "
			+ "LOWER(p.address.city.name) LIKE LOWER(concat('%', ?1, '%'))")
	Page<Pharmacy> search(String query, Pageable pageable);
}
