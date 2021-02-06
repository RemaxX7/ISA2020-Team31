package internet.software.architectures.team31.isapharmacy.service;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;

public interface MedicineService {

	Medicine findById(Long id);
}
