package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {

	Collection<Pharmacist> findAllByPharmacyId(Long id);
	List<Pharmacist> findAll();
	
}
