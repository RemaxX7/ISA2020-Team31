package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;

public interface PharmacyService {
	Pharmacy save(Pharmacy pharmacy);
	Collection<Pharmacy> findAll();
	Pharmacy findById(Long id);
}
