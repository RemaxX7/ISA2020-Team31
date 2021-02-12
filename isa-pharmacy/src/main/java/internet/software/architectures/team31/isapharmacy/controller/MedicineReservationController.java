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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservation;
import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservationStatus;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReservationCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReservationViewDTO;
import internet.software.architectures.team31.isapharmacy.exception.CancelMedicineReservationException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;
import internet.software.architectures.team31.isapharmacy.service.MedicineReservationService;

@RestController
@RequestMapping(value = "api/reservations")
public class MedicineReservationController {

	@Autowired
	private MedicineReservationService medicineReservationService;
	
	@PostMapping(value = "/save")
	public ResponseEntity<MedicineReservation> save(@RequestBody MedicineReservationCreateDTO dto) throws PenaltyException {
		dto.setPickUpDate(dto.getPickUpDate().plusDays(1L));
		return new ResponseEntity<>(medicineReservationService.save(dto), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<Collection<MedicineReservation>> findAll() {
		return new ResponseEntity<>(medicineReservationService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/finished/{page}")
	public ResponseEntity<Page<MedicineReservationViewDTO>> findAllFinishedByPatient(@PathVariable Integer page) {
		return new ResponseEntity<>(medicineReservationService.findAllByPatientAndMedicineReservationStatus(
				MedicineReservationStatus.FINISHED, PageRequest.of(page, 5, Sort.by("pickUpDate"))), HttpStatus.OK);
	}
	
	@GetMapping(value = "/created/{page}")
	public ResponseEntity<Page<MedicineReservationViewDTO>> findAllCreatedByPatient(@PathVariable Integer page) {
		return new ResponseEntity<>(medicineReservationService.findAllByPatientAndMedicineReservationStatus(
				MedicineReservationStatus.CREATED, PageRequest.of(page, 5, Sort.by("pickUpDate"))), HttpStatus.OK);
	}
	
	@PostMapping(value = "/cancel/{id}")
	public ResponseEntity<MedicineReservation> cancel(@PathVariable Long id) throws CancelMedicineReservationException {
		return new ResponseEntity<>(medicineReservationService.cancel(id), HttpStatus.OK);
	}
	@GetMapping(value = "/pickedup/{code}")
	public ResponseEntity<MedicineReservation> cancel(@PathVariable String code) throws CancelMedicineReservationException{
		return new ResponseEntity<>(medicineReservationService.closeRequest(code), HttpStatus.OK);
	}
}
