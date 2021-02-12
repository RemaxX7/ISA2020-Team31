package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import internet.software.architectures.team31.isapharmacy.domain.users.Dermatologist;
import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {

	Collection<Pharmacist> findAllByPharmacyId(Long id);
	List<Pharmacist> findAll();
	@Query(value ="SELECT * FROM users s " + 
			"WHERE (LOWER(concat(s.name,' ',s.surname)) LIKE LOWER(concat('%', :query,'%')) " + 
			"	   or LOWER(concat(s.surname,' ',s.name)) LIKE LOWER(concat('%', :query,'%')))" + 
			"AND s.type='Pharmacist'", nativeQuery = true)
	List<Pharmacist> search(@Param("query")String query);
	
}
