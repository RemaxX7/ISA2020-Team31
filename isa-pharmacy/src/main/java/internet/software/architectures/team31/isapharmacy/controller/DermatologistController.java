package internet.software.architectures.team31.isapharmacy.controller;


import java.util.List;

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

import internet.software.architectures.team31.isapharmacy.domain.users.Dermatologist;
import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;
import internet.software.architectures.team31.isapharmacy.dto.MedicineViewDTO;
import internet.software.architectures.team31.isapharmacy.service.DermatologistService;

@RestController
@RequestMapping(value = "auth/dermatologist")
public class DermatologistController {
	
	@Autowired
	private DermatologistService dermatologistService;
	
	@GetMapping("/all/{pharmacyId}")
	public ResponseEntity<List<Dermatologist>> findAllByPharmacy(@PathVariable Long pharmacyId) {
		return new ResponseEntity<List<Dermatologist>>(this.dermatologistService.findAllByPharmacy(pharmacyId), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Dermatologist>> findAll(){
		return new ResponseEntity<List<Dermatologist>>(this.dermatologistService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Dermatologist> findById(Long id){
		return new ResponseEntity<>(this.dermatologistService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/{query}")
	public ResponseEntity<List<Dermatologist>> search(@PathVariable String query) {
		return new ResponseEntity<>(dermatologistService.search(query), HttpStatus.OK);
	}
}
