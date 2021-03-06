package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.InventoryItem;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;
import internet.software.architectures.team31.isapharmacy.dto.MedicineViewDTO;
import internet.software.architectures.team31.isapharmacy.repository.MedicineRepository;
import internet.software.architectures.team31.isapharmacy.service.EmailService;
import internet.software.architectures.team31.isapharmacy.service.InventoryItemService;
import internet.software.architectures.team31.isapharmacy.service.MedicineReviewService;
import internet.software.architectures.team31.isapharmacy.service.MedicineService;
import internet.software.architectures.team31.isapharmacy.service.PharmacistService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;

@Service
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;
	@Autowired
	private MedicineReviewService medicineReviewService;
	@Autowired
	private PatientServiceImpl patientService;
	@Autowired
	private PharmacyService pharmacyService;
	@Autowired
	private InventoryItemService inventoryService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private PharmacistService pharmacistService;

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

	@Override
	public List<Medicine> findByMedicineIds(List<Long> medicineIdList) {
		return medicineRepository.findByMedicineIds(medicineIdList);
	}
	
	public String findAvailableMedicineCount(String name, String id) throws Exception {
		Medicine med = findByName(name);
		Double broj = (double) 0;
		Pharmacy pharm = pharmacyService.findById(Long.parseLong(id));
		List<InventoryItem> item = inventoryService.findAll();
		for (InventoryItem inventoryItem : item) {
			if(inventoryItem.getMedicine().getName().toLowerCase().equals(name.toLowerCase())) {
				broj = inventoryItem.getQuantity();
			}
		}
		if(broj==0) {
			//emailService.sendEmail(admin apoteke, "Nedostatak lekova", "Na stanju je 0 "+ med);
			throw new Exception();
		}
		return broj.toString();
	}
}
