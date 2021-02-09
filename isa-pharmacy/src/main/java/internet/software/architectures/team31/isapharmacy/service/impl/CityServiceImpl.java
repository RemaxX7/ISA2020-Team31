package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.location.City;
import internet.software.architectures.team31.isapharmacy.repository.CityRepository;
import internet.software.architectures.team31.isapharmacy.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Override
	public City findById(Long id) {
		return cityRepository.findById(id).orElse(null);
	}
	
	@Override
	public Collection<City> findAllByCountryId(Long id) {
		return cityRepository.findAllByCountryIdOrderByNameAsc(id);
	}
}
