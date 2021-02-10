package internet.software.architectures.team31.isapharmacy.service;

import java.util.List;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;

public interface MedicineService {

	Medicine findById(Long id);
	List<Medicine> findAll();
	Medicine findByName(String name);
}
