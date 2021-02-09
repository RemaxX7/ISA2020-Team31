package internet.software.architectures.team31.isapharmacy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.dto.MedicineViewDTO;

public interface MedicineService {

	Medicine findById(Long id);
	Page<MedicineViewDTO> findAll(Pageable pageable);
	Page<MedicineViewDTO> search(String query, Pageable pageable);
}
