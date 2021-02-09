package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.location.City;

public interface CityRepository extends JpaRepository<City, Long> {

	Collection<City> findAllByCountryIdOrderByNameAsc(Long id);
}
