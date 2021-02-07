package internet.software.architectures.team31.isapharmacy.service;

import java.util.List;

import internet.software.architectures.team31.isapharmacy.domain.users.Dermatologist;

public interface DermatologistService {
	
	List<Dermatologist> findAllByPharmacy(Long id);
}
