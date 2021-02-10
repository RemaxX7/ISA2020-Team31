package internet.software.architectures.team31.isapharmacy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
	
	User findByEmail(String email);
	
	@Query(value = "SELECT u.type FROM users u  WHERE u.id=:id", nativeQuery = true )
	String findTypeById(@Param("id")Long id);
	List<User> findAll();
	
	User findByUidn(String uidn);
	
	Patient findByActivationToken(String token);
}
