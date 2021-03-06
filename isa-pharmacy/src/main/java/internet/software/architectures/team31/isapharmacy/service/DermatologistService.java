package internet.software.architectures.team31.isapharmacy.service;

import java.util.List;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.domain.users.Dermatologist;

public interface DermatologistService {
	
	List<Dermatologist> findAllByPharmacy(Long id);
	List<Dermatologist> findAll();
	Dermatologist findById(Long id);
	List<Dermatologist> search(String query);
}
