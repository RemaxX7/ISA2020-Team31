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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.dto.AdditionalExamSchedulingDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentFinalizationDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentScheduleDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentViewDTO;
import internet.software.architectures.team31.isapharmacy.dto.CounselingCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;
import internet.software.architectures.team31.isapharmacy.service.CounselingService;
import internet.software.architectures.team31.isapharmacy.service.impl.PatientServiceImpl;

@RestController
@RequestMapping(value = "api/appointments/counselings")
public class CounselingController {

	@Autowired
	private CounselingService counselingService;
	@Autowired
	private PatientServiceImpl patientService;
	
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
	
	@GetMapping(value = "/finished/{page}/{sort}")
	public ResponseEntity<Page<AppointmentViewDTO>> findFinishedByPatient(@PathVariable Integer page, @PathVariable String sort) {
		return new ResponseEntity<>(counselingService.findAllByPatientIdAndAppointmentStatus(AppointmentStatus.FINISHED, PageRequest.of(page, 5, Sort.by(sort))), HttpStatus.OK);
	}
	
	@GetMapping(value = "/created/{page}/{sort}")
	public ResponseEntity<Page<AppointmentViewDTO>> findCreatedByPatient(@PathVariable Integer page, @PathVariable String sort) {
		return new ResponseEntity<>(counselingService.findAllByPatientIdAndAppointmentStatus(AppointmentStatus.OCCUPIED, PageRequest.of(page, 5, Sort.by(sort))), HttpStatus.OK);
	}	
	
	@PutMapping(value = "/schedule")
	public ResponseEntity<Counseling> shedule(@RequestBody AppointmentScheduleDTO dto) throws PenaltyException, AppointmentNotFreeException {
		return new ResponseEntity<>(counselingService.schedule(dto), HttpStatus.OK);
	}
	
	@PostMapping(value = "/cancel/{id}")
	public ResponseEntity<Counseling> cancel(@PathVariable Long id) throws CancelAppointmentException {
		return new ResponseEntity<>(counselingService.cancel(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/pharmacistpenalize/{id}")
	public ResponseEntity<Counseling>penalize(@PathVariable String id){
		return new ResponseEntity<>(patientService.pharmacistPenalize(id),HttpStatus.OK);
	}
	
	@PostMapping(value = "/finalizeappointmentpharmacist")
	public ResponseEntity<Counseling> updateFinishedExam(@RequestBody AppointmentFinalizationDTO dto){
		return new ResponseEntity<>(counselingService.finalizeExam(dto),HttpStatus.OK);
	}
	
	@PostMapping(value = "/schedulenewcounseling")
	public ResponseEntity<Counseling> scheduleAdditionalConsultation(@RequestBody AdditionalExamSchedulingDTO dto){
		return new ResponseEntity<>(counselingService.scheduleAdditionalConsultation(dto),HttpStatus.OK);
	}
}
