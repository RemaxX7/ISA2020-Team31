package internet.software.architectures.team31.isapharmacy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.repository.MedicineRepository;
import internet.software.architectures.team31.isapharmacy.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;

	@Override
	public Medicine findById(Long id) {
		return medicineRepository.findById(id).orElse(null);
	}
}
