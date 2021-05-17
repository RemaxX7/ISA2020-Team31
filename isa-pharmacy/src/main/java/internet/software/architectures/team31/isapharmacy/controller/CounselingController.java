package internet.software.architectures.team31.isapharmacy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.dto.AdditionalExamSchedulingDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentFinalizationDTO;
import internet.software.architectures.team31.isapharmacy.dto.AppointmentViewDTO;
import internet.software.architectures.team31.isapharmacy.dto.CounselingCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.FindAvailablePharmacistsDTO;
import internet.software.architectures.team31.isapharmacy.dto.UserViewDTO;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.CounselingAlreadyScheduledException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidInputException;
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
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "/schedule")
	public ResponseEntity<Counseling> save(@RequestBody CounselingCreateDTO dto) throws PenaltyException, CounselingAlreadyScheduledException {
		dto.setStartDateTime(dto.getStartDateTime().plusHours(1L));
		return new ResponseEntity<>(counselingService.save(dto), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<Collection<Counseling>> findAll() {
		return new ResponseEntity<>(counselingService.findAll(), HttpStatus.OK);
	}
	@GetMapping(value = "/allactive")
	public ResponseEntity<Collection<Counseling>> findAllActive() {
		return new ResponseEntity<>(counselingService.findAllActive(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/free")
	public ResponseEntity<Collection<Counseling>> findFree() {
		return new ResponseEntity<>(counselingService.findAllByAppointmentStatus(AppointmentStatus.FREE), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/finished/{page}/{sort}")
	public ResponseEntity<Page<AppointmentViewDTO>> findFinishedByPatient(@PathVariable Integer page, @PathVariable String sort) {
		return new ResponseEntity<>(counselingService.findAllByPatientIdAndAppointmentStatus(AppointmentStatus.FINISHED, PageRequest.of(page, 5, Sort.by(sort))), HttpStatus.OK);
	}
	
	@GetMapping(value = "/findbyid/{id}")
	public ResponseEntity<Counseling> findById(@PathVariable Long id) {
		return new ResponseEntity<>(counselingService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/finished/patient/{id}")
	public ResponseEntity<Collection<Counseling>> findFinishedByPatient(@PathVariable Long id) {
		return new ResponseEntity<>(counselingService.findAllByPatientIdAndAppointmentStatus(id,
				AppointmentStatus.FINISHED), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping(value = "/created/{page}/{sort}")
	public ResponseEntity<Page<AppointmentViewDTO>> findCreatedByPatient(@PathVariable Integer page, @PathVariable String sort) {
		return new ResponseEntity<>(counselingService.findAllByPatientIdAndAppointmentStatus(AppointmentStatus.OCCUPIED, PageRequest.of(page, 5, Sort.by(sort))), HttpStatus.OK);
	}	
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "/cancel/{id}")
	public ResponseEntity<Boolean> cancel(@PathVariable Long id) throws CancelAppointmentException {
		return new ResponseEntity<>(counselingService.cancel(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/pharmacistpenalize/{id}/{date}/{pharmuidn}")
	public ResponseEntity<Counseling>penalize(@PathVariable String id,@PathVariable String date,@PathVariable String pharmuidn){
		return new ResponseEntity<>(patientService.pharmacistPenalize(id,date,pharmuidn),HttpStatus.OK);
	}
	

	@PostMapping(value = "/finalizeappointmentpharmacist/{quant}")
	public ResponseEntity<Counseling> updateFinishedExam(@RequestBody AppointmentFinalizationDTO dto,@PathVariable String quant) throws InvalidInputException{
		return new ResponseEntity<>(counselingService.finalizeExam(dto,quant),HttpStatus.OK);
	}
	
	@PostMapping(value = "/schedulenewcounseling")
	public ResponseEntity<Counseling> scheduleAdditionalConsultation(@RequestBody AdditionalExamSchedulingDTO dto){
		return new ResponseEntity<>(counselingService.scheduleAdditionalConsultation(dto),HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "/available")
	public ResponseEntity<Collection<UserViewDTO>> getAvailablePharmacists(@RequestBody FindAvailablePharmacistsDTO dto) {
		return new ResponseEntity<>(counselingService.findAvailablePharmacists(dto.getDateTime().plusHours(1L), dto.getPharmacyId()), HttpStatus.OK);
	}
	
	@PostMapping(value = "/update/points/{counselingId}/{points}")
	public ResponseEntity<Counseling> updateLoyaltyPointsForSpecificCounseling(@PathVariable Long counselingId, @PathVariable Integer points){
		return new ResponseEntity<>(counselingService.updatePoints(counselingId, points), HttpStatus.OK);
	}
}
