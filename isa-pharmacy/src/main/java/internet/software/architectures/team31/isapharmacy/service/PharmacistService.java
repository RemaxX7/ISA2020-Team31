package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;

public interface PharmacistService {
	
	Collection<Pharmacist> findAllByPharmacyId(Long id);

}
