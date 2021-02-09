package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.location.Country;
import internet.software.architectures.team31.isapharmacy.repository.CountryRepository;
import internet.software.architectures.team31.isapharmacy.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;
	
	@Override
	public Collection<Country> findAll() {
		return countryRepository.findAllByOrderByNameAsc();
	}
}
