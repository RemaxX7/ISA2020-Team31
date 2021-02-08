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
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentScheduleDTO;
import internet.software.architectures.team31.isapharmacy.dto.ExamCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;
import internet.software.architectures.team31.isapharmacy.service.ExamService;

@RestController
@RequestMapping(value = "api/appointments/exams")
public class ExamController {

	@Autowired
	private ExamService examService;
	
	@PostMapping(value = "/save")
	public ResponseEntity<Exam> save(@RequestBody ExamCreateDTO dto) {
		return new ResponseEntity<>(examService.save(dto), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<Collection<Exam>> findAll() {
		return new ResponseEntity<>(examService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/free")
	public ResponseEntity<Collection<Exam>> findFree() {
		return new ResponseEntity<>(examService.findAllByAppointmentStatus(AppointmentStatus.FREE), HttpStatus.OK);
	}
	
	@GetMapping(value = "/finished/patient/{id}")
	public ResponseEntity<Collection<Counseling>> findFinishedByPatient(@PathVariable Long id) {
		return new ResponseEntity<>(examService.findAllByPatientIdAndAppointmentStatus(id,
				AppointmentStatus.FINISHED), HttpStatus.OK);
	}
	
	@GetMapping(value = "/created/patient/{id}")
	public ResponseEntity<Collection<Counseling>> findCreatedByPatient(@PathVariable Long id) {
		return new ResponseEntity<>(examService.findAllByPatientIdAndAppointmentStatus(id,
				AppointmentStatus.OCCUPIED), HttpStatus.OK);
	}
	
	@PutMapping(value = "/schedule")
	public ResponseEntity<Exam> shedule(@RequestBody AppointmentScheduleDTO dto) throws PenaltyException, AppointmentNotFreeException {
		return new ResponseEntity<>(examService.schedule(dto), HttpStatus.OK);
	}
	
	@PutMapping(value = "/cancel/{id}")
	public ResponseEntity<Exam> cancel(@PathVariable Long id) throws CancelAppointmentException {
		return new ResponseEntity<>(examService.cancel(id), HttpStatus.OK);
	}
}
