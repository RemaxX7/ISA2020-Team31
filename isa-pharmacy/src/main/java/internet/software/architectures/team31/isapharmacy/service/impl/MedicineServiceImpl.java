package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.repository.MedicineRepository;
import internet.software.architectures.team31.isapharmacy.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private PatientServiceImpl patientService;
	@Override
	public Medicine findById(Long id) {
		return medicineRepository.findById(id).orElse(null);
	}
	
	public List<Medicine> findAll() {
		return (List<Medicine>) medicineRepository.findAll();
	}
	public List<Medicine> findAllMedicineForPatient(String uidn){
		Patient patient = patientService.findByUidn(uidn);
		ArrayList<Medicine> medicineList = (ArrayList<Medicine>) findAll();
		for (Medicine med : medicineList) {
			for (Medicine al : patient.getAllergies()) {
				if(al.equals(med))
					medicineList.remove(med);
			}
		}
		return medicineList;
	}
	
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
