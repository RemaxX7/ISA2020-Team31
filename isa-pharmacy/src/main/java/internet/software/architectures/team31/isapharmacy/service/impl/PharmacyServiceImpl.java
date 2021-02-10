package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.dto.PharmacyViewDTO;
import internet.software.architectures.team31.isapharmacy.repository.PharmacyRepository;
import internet.software.architectures.team31.isapharmacy.service.PharmacyReviewService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;

@Service
public class PharmacyServiceImpl implements PharmacyService {

	@Autowired
	private PharmacyRepository pharmacyRepository;

	@Autowired
	private PharmacyReviewService pharmacyReviewService;

	@Override
	public Pharmacy save(Pharmacy pharmacy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Pharmacy> findAll() {
		return pharmacyRepository.findAll();
	}

	@Override
	public Page<PharmacyViewDTO> findAll(Pageable pageable) {
		Page<PharmacyViewDTO> pharmacies = pharmacyRepository.findAll(pageable).map(pharmacy -> new PharmacyViewDTO(pharmacy));
		pharmacies.forEach(pharmacy -> pharmacy.setRate(pharmacyReviewService.calculatePharmacyScore(pharmacy.getId())));
		return pharmacies;
	}
	
	@Override
	public Page<PharmacyViewDTO> search(String query, Pageable pageable) {
		Page<PharmacyViewDTO> pharmacies = pharmacyRepository.search(query, pageable).map(pharmacy -> new PharmacyViewDTO(pharmacy));
		pharmacies.forEach(pharmacy -> pharmacy.setRate(pharmacyReviewService.calculatePharmacyScore(pharmacy.getId())));
		return pharmacies;
	}

	@Override
	public Pharmacy findById(Long id) {
		return pharmacyRepository.findById(id).orElse(null);
	}

}
