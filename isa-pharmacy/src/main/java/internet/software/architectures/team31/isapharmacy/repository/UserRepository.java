package internet.software.architectures.team31.isapharmacy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
	
	User findByEmail(String email);
	
	List<User> findAll();
	
	User findByUidn(String uidn);
	
	Patient findByActivationToken(String token);
}
