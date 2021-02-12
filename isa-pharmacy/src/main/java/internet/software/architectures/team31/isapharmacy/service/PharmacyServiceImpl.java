package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.repository.PharmacyRepository;

@Service
public class PharmacyServiceImpl implements PharmacyService {

	@Autowired
	private PharmacyRepository pharmacyRepository;

	@Override
	public Pharmacy save(Pharmacy pharmacy) {
		return this.pharmacyRepository.save(pharmacy);
	}

	@Override
	public Collection<Pharmacy> findAll() {
		return pharmacyRepository.findAll();
	}

	@Override
	public Pharmacy findById(Long id) {
		return pharmacyRepository.findById(id).orElse(null);
	}
	
}
