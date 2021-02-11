package internet.software.architectures.team31.isapharmacy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservation;
import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservationStatus;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReservationCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.CancelMedicineReservationException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;
import internet.software.architectures.team31.isapharmacy.service.MedicineReservationService;

@RestController
@RequestMapping(value = "auth/reservations")
public class MedicineReservationController {

	@Autowired
	private MedicineReservationService medicineReservationService;
	
	@PostMapping(value = "/save")
	public ResponseEntity<MedicineReservation> save(@RequestBody MedicineReservationCreateDTO dto) throws PenaltyException {
		return new ResponseEntity<>(medicineReservationService.save(dto), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<Collection<MedicineReservation>> findAll() {
		return new ResponseEntity<>(medicineReservationService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/patient/{id}")
	public ResponseEntity<Collection<MedicineReservation>> findAllByPatient(@PathVariable Long id) {
		return new ResponseEntity<>(medicineReservationService.findAllByPatientId(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/finished/patient/{id}")
	public ResponseEntity<Collection<MedicineReservation>> findAllFinishedByPatient(@PathVariable Long id) {
		return new ResponseEntity<>(medicineReservationService.findAllByPatientIdAndMedicineReservationStatus(id,
				MedicineReservationStatus.FINISHED), HttpStatus.OK);
	}
	
	@GetMapping(value = "/created/patient/{id}")
	public ResponseEntity<Collection<MedicineReservation>> findAllCreatedByPatient(@PathVariable Long id) {
		return new ResponseEntity<>(medicineReservationService.findAllByPatientIdAndMedicineReservationStatus(id,
				MedicineReservationStatus.CREATED), HttpStatus.OK);
	}
	
	@PutMapping(value = "/cancel/{id}")
	public ResponseEntity<MedicineReservation> cancel(@PathVariable Long id) throws CancelMedicineReservationException {
		return new ResponseEntity<>(medicineReservationService.cancel(id), HttpStatus.OK);
	}
	@GetMapping(value = "/pickedup/{code}")
	public ResponseEntity<MedicineReservation> cancel(@PathVariable String code) throws CancelMedicineReservationException{
		return new ResponseEntity<>(medicineReservationService.closeRequest(code), HttpStatus.OK);
	}
}
