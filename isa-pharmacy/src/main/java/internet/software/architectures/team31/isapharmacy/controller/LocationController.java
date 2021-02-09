package internet.software.architectures.team31.isapharmacy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.location.City;
import internet.software.architectures.team31.isapharmacy.domain.location.Country;
import internet.software.architectures.team31.isapharmacy.service.CityService;
import internet.software.architectures.team31.isapharmacy.service.CountryService;

@RestController
@RequestMapping(value = "api/location")
public class LocationController {

	@Autowired
	private CountryService countryService;
	@Autowired
	private CityService cityService;
	
	@GetMapping(value = "/countries")
	public ResponseEntity<Collection<Country>> findAllCounties() {
		return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/cities/{countryId}")
	public ResponseEntity<Collection<City>> findAllCitiesByCountry(@PathVariable Long countryId) {
		return new ResponseEntity<>(cityService.findAllByCountryId(countryId), HttpStatus.OK);
	}
}
