package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.users.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findById(Long id);
	
	User findByUsername(String username);
	
	User findByEmail(String email);
}
