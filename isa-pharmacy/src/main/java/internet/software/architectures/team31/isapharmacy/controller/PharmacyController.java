package internet.software.architectures.team31.isapharmacy.controller;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.dto.InventoryItemCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.PharmacyViewDTO;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;

@RestController
@RequestMapping(value = "api/pharmacy")
public class PharmacyController {
	
	@Autowired
	private PharmacyService pharmacyService;

	@GetMapping("/{pharmacyId}")
	public Pharmacy loadById(@PathVariable Long pharmacyId) {
		return this.pharmacyService.findById(pharmacyId);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Collection<PharmacyViewDTO>> findAll() {
		return new ResponseEntity<>(pharmacyService.findAll().stream()
				.map(pharmacy -> new PharmacyViewDTO(pharmacy)).collect(Collectors.toList()), HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/{page}")
	public ResponseEntity<Page<PharmacyViewDTO>> findAll(@PathVariable Integer page) {
		return new ResponseEntity<>(pharmacyService.findAll(PageRequest.of(page, 5, Sort.by("name"))), HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/{query}/{page}")
	public ResponseEntity<Page<PharmacyViewDTO>> search(@PathVariable String query, @PathVariable Integer page) {
		return new ResponseEntity<>(pharmacyService.search(query, PageRequest.of(page, 5, Sort.by("name"))), HttpStatus.OK);
	}
	
	@PostMapping(value = "/available/counseling")
	public ResponseEntity<Collection<PharmacyViewDTO>> findAvailableForCounseling(@RequestBody LocalDateTime dateTime) {
		return new ResponseEntity<>(pharmacyService.findAllAvailableForCounseling(dateTime.plusHours(1L)), HttpStatus.OK);
	}

	@PostMapping(value="/addItem")
	public ResponseEntity<Pharmacy> addInventoryItem(@RequestBody InventoryItemCreateDTO dto) {
		return new ResponseEntity<>(this.pharmacyService.addNewItem(dto),HttpStatus.CREATED);
	}
}
