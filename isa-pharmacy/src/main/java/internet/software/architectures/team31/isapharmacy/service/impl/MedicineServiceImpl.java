package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.dto.MedicineViewDTO;
import internet.software.architectures.team31.isapharmacy.repository.MedicineRepository;
import internet.software.architectures.team31.isapharmacy.service.MedicineReviewService;
import internet.software.architectures.team31.isapharmacy.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;
	@Autowired
	private MedicineReviewService medicineReviewService;
	@Autowired
	private PatientServiceImpl patientService;

	@Override
	public Medicine findById(Long id) {
		return medicineRepository.findById(id).orElse(null);
	}

	@Override
	public Page<MedicineViewDTO> findAll(Pageable pageable) {
		Page<MedicineViewDTO> medicines = medicineRepository.findAll(pageable).map(medicine -> new MedicineViewDTO(medicine));
		medicines.forEach(medicine -> medicine.setRate(medicineReviewService.calculateMedicineScore(medicine.getId())));
		return medicines;
	}

	@Override
	public Page<MedicineViewDTO> search(String query, Pageable pageable) {
		Page<MedicineViewDTO> medicines = medicineRepository.search(query, pageable).map(pharmacy -> new MedicineViewDTO(pharmacy));
		medicines.forEach(medicine -> medicine.setRate(medicineReviewService.calculateMedicineScore(medicine.getId())));
		return medicines;
	}
	
	@Override
	public List<Medicine> findAll() {
		return (List<Medicine>) medicineRepository.findAll();
	}
	
	@Override
	public List<Medicine> findAllMedicineForPatient(String uidn){
		Patient patient = patientService.findByUidn(uidn);
		ArrayList<Medicine> medListRemove = new ArrayList<Medicine>();
		ArrayList<Medicine> medicineList = (ArrayList<Medicine>) findAll();
		for (Medicine med : medicineList) {
			for (Medicine al : patient.getAllergies()) {
				if(al.equals(med))
					medListRemove.add(med);
			}
		}
		medicineList.removeAll(medListRemove);
		return medicineList;
	}
	
	@Override
	public Medicine findCompositionForMedicine(String name) {
		List<Medicine> medicines = findAll();
		for (Medicine med : medicines) {
			if(med.getName().toLowerCase().equals(name)) {
				return med;
			}
		}
		return null;
	}

	@Override
	public Medicine findByName(String name) {
		return medicineRepository.findByName(name);
	}
}
