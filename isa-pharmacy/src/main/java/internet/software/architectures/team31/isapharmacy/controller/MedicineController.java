package internet.software.architectures.team31.isapharmacy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.dto.MedicineViewDTO;
import internet.software.architectures.team31.isapharmacy.service.MedicineService;


@RestController
@RequestMapping(value = "api/medicine")
public class MedicineController {
	
	@Autowired
	private MedicineService medicineService;

	@GetMapping(value = "/all/{page}")
	public ResponseEntity<Page<MedicineViewDTO>> findAll(@PathVariable Integer page) {
		return new ResponseEntity<>(medicineService.findAll(PageRequest.of(page, 5, Sort.by("name"))), HttpStatus.OK);
	}

	@GetMapping(value = "/search/{query}/{page}")
	public ResponseEntity<Page<MedicineViewDTO>> search(@PathVariable String query, @PathVariable Integer page) {
		return new ResponseEntity<>(medicineService.search(query, PageRequest.of(page, 5, Sort.by("name"))), HttpStatus.OK);
	}

	@GetMapping(value = "/medicineforpatient/{uidn}")
	public ResponseEntity<Collection<Medicine>> findAllMedicineForPatient(@PathVariable String uidn) {
		return new ResponseEntity<>(medicineService.findAllMedicineForPatient(uidn),HttpStatus.OK);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<Collection<Medicine>> findAllMedicine(){
		return new ResponseEntity<>(medicineService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/composition/{name}")
	public ResponseEntity<Medicine> findCompositionForMedicine(@PathVariable String name){
		return new ResponseEntity<>(medicineService.findCompositionForMedicine(name),HttpStatus.OK);
	}
}
