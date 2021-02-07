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

import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.dto.CounselingCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentScheduleDTO;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;
import internet.software.architectures.team31.isapharmacy.service.CounselingService;

@RestController
@RequestMapping(value = "api/appointments/counselings")
public class CounselingController {

	@Autowired
	private CounselingService counselingService;
	
	@PostMapping(value = "/save")
	public ResponseEntity<Counseling> save(@RequestBody CounselingCreateDTO dto) {
		return new ResponseEntity<>(counselingService.save(dto), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<Collection<Counseling>> findAll() {
		return new ResponseEntity<>(counselingService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/free")
	public ResponseEntity<Collection<Counseling>> findFree() {
		return new ResponseEntity<>(counselingService.findAllByAppointmentStatus(AppointmentStatus.FREE), HttpStatus.OK);
	}
	
	@GetMapping(value = "/finished/patient/{id}")
	public ResponseEntity<Collection<Counseling>> findFinishedByPatient(@PathVariable Long id) {
		return new ResponseEntity<>(counselingService.findAllByPatientIdAndAppointmentStatus(id,
				AppointmentStatus.FINISHED), HttpStatus.OK);
	}
	
	@GetMapping(value = "/created/patient/{id}")
	public ResponseEntity<Collection<Counseling>> findCreatedByPatient(@PathVariable Long id) {
		return new ResponseEntity<>(counselingService.findAllByPatientIdAndAppointmentStatus(id,
				AppointmentStatus.OCCUPIED), HttpStatus.OK);
	}
	
	@PutMapping(value = "/schedule")
	public ResponseEntity<Counseling> shedule(@RequestBody AppointmentScheduleDTO dto) throws PenaltyException, AppointmentNotFreeException {
		return new ResponseEntity<>(counselingService.schedule(dto), HttpStatus.OK);
	}
	
	@PutMapping(value = "/cancel/{id}")
	public ResponseEntity<Counseling> cancel(@PathVariable Long id) throws CancelAppointmentException {
		return new ResponseEntity<>(counselingService.cancel(id), HttpStatus.OK);
	}
}