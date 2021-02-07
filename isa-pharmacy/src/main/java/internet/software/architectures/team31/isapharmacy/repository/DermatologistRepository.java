package internet.software.architectures.team31.isapharmacy.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import internet.software.architectures.team31.isapharmacy.domain.users.Dermatologist;


public interface DermatologistRepository extends JpaRepository<Dermatologist, Long> {
	@Query(value = "SELECT * FROM"
			+ " users u  WHERE u.id = (select p.dermatologist_id from users_pharmacies p where p.pharmacies_id=:id)", nativeQuery = true )
	List<Dermatologist> findAllByPharmacy(@Param("id")Long id);
}
