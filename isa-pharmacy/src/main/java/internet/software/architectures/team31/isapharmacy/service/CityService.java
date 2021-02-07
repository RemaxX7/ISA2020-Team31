package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.location.City;

@Service
public interface CityService {

	City findById(Long id);
	Collection<City> findAllByCountryId(Long id);
}
