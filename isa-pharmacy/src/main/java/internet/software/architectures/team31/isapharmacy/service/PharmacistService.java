package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;
import java.util.List;

import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;
import internet.software.architectures.team31.isapharmacy.dto.UserRegisterDTO;
import internet.software.architectures.team31.isapharmacy.exception.UsernameNotUniqueException;

public interface PharmacistService {
	
	Collection<Pharmacist> findAllByPharmacyId(Long id);
	List<Pharmacist> findAll();
	Pharmacist findById(Long id); 
	Pharmacist register(UserRegisterDTO dto,Long pharmacyId) throws UsernameNotUniqueException;
	List<Pharmacist> search(String query);
}
