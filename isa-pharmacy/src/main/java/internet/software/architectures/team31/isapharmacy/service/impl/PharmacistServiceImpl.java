package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;
import internet.software.architectures.team31.isapharmacy.dto.UserRegisterDTO;
import internet.software.architectures.team31.isapharmacy.exception.UsernameNotUniqueException;
import internet.software.architectures.team31.isapharmacy.repository.PharmacistRepository;
import internet.software.architectures.team31.isapharmacy.repository.UserRepository;
import internet.software.architectures.team31.isapharmacy.service.AuthorityService;
import internet.software.architectures.team31.isapharmacy.service.CityService;
import internet.software.architectures.team31.isapharmacy.service.PharmacistService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@Service
public class PharmacistServiceImpl implements PharmacistService  {

	@Autowired
	private PharmacistRepository pharmacistRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PharmacyService pharmacyService;
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private CityService cityService;

	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Collection<Pharmacist> findAllByPharmacyId(Long id) {
		return pharmacistRepository.findAllByPharmacyId(id);
	}
	
	@Override
	public List<Pharmacist> findAll(){
		return pharmacistRepository.findAll();
	}

	@Override
	public Pharmacist findById(Long id) {
		return pharmacistRepository.findById(id).orElse(null);
	}

	@Override
	public Pharmacist register(UserRegisterDTO dto, Long pharmacyId) throws UsernameNotUniqueException {
		if(userService.findByEmail(dto.getEmail()) != null) {
			throw new UsernameNotUniqueException("Username " + dto.getEmail() + " is already registered.");
		}
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		Pharmacy pharmacy =this.pharmacyService.findById(pharmacyId);
		Pharmacist pharmacist = new Pharmacist(dto,pharmacy);
		if(pharmacy!=null) {
			
			pharmacist.getAddress().setCity(cityService.findById(dto.getAddress().getCityId()));
			pharmacist.setAuthorities(authorityService.findByname("ROLE_PHARMACIST"));
			return pharmacistRepository.save(pharmacist);
		}
		return null;
		
	}

	@Override
	public List<Pharmacist> search(String query) {
		return this.pharmacistRepository.search(query);
	}



}
