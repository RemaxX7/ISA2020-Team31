package internet.software.architectures.team31.isapharmacy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.service.impl.MedicineServiceImpl;
import internet.software.architectures.team31.isapharmacy.service.impl.PatientServiceImpl;

@RestController
@RequestMapping(value = "auth/medicine")
public class MedicineContoller {
	
	@Autowired
	private MedicineServiceImpl medicineService;
	
	
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
