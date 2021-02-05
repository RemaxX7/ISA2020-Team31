package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.action.Action;

public interface ActionRepository extends JpaRepository<Action, Long> {
	
	Optional<Action> findById(Long id);
}
