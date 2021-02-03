package internet.software.architectures.team31.isapharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.users.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

	Authority findByName(String name);
}
