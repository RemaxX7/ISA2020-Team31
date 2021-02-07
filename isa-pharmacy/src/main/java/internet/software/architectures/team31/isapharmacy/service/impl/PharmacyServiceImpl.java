package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.repository.PharmacyRepository;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;

@Service
public class PharmacyServiceImpl implements PharmacyService {

	@Autowired
	private PharmacyRepository pharmacyRepository;

	@Override
	public Pharmacy save(Pharmacy pharmacy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Pharmacy> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pharmacy findById(Long id) {
		return pharmacyRepository.findById(id).orElse(null);
	}
}