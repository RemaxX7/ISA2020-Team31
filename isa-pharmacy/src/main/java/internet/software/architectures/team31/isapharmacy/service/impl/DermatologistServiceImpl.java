package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.users.Dermatologist;
import internet.software.architectures.team31.isapharmacy.repository.DermatologistRepository;
import internet.software.architectures.team31.isapharmacy.service.DermatologistService;

@Service
public class DermatologistServiceImpl implements DermatologistService {

	@Autowired
	private DermatologistRepository dermatologistRepository;
	
	
	@Override
	public List<Dermatologist> findAllByPharmacy(Long id) {
		return dermatologistRepository.findAllByPharmacy(id);
	}
	
	@Override
	public List<Dermatologist> findAll(){
		return dermatologistRepository.findAll();
	}

	@Override
	public Dermatologist findById(Long id) {
		return dermatologistRepository.findById(id).orElse(null);
	}

	@Override
	public List<Dermatologist> search(String query) {
		return this.dermatologistRepository.search(query);
	}
}
