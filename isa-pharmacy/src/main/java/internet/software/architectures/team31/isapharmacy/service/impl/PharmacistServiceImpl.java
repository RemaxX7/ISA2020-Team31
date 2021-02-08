package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;
import internet.software.architectures.team31.isapharmacy.repository.PharmacistRepository;
import internet.software.architectures.team31.isapharmacy.service.PharmacistService;

@Service
public class PharmacistServiceImpl implements PharmacistService  {

	@Autowired
	private PharmacistRepository pharmacistRepository;
	
	@Override
	public Collection<Pharmacist> findAllByPharmacyId(Long id) {
		return pharmacistRepository.findAllByPharmacyId(id);
	}
	
	@Override
	public List<Pharmacist> findAll(){
		return pharmacistRepository.findAll();
	}

}
