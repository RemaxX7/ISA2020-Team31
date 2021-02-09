package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.location.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

	Collection<Country> findAllByOrderByNameAsc();
}
