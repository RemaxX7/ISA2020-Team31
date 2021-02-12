package internet.software.architectures.team31.isapharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.patient.EmployeeReview;
import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReview;
import internet.software.architectures.team31.isapharmacy.domain.patient.PharmacyReview;
import internet.software.architectures.team31.isapharmacy.dto.EmployeeReviewCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReviewCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.PharmacyReviewCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.InvalidReviewException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidScoreException;
import internet.software.architectures.team31.isapharmacy.service.EmployeeReviewService;
import internet.software.architectures.team31.isapharmacy.service.MedicineReviewService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyReviewService;

@RestController
@RequestMapping(value = "api/reviews")
public class ReviewController {

	@Autowired
	private PharmacyReviewService pharmacyReviewService;
	@Autowired
	private MedicineReviewService medicineReviewService;
	@Autowired
	private EmployeeReviewService employeeReviewService;
	
	@GetMapping(value = "/pharmacy/{id}")
	public ResponseEntity<Integer> findPharmacyScore(@PathVariable Long id) throws InvalidScoreException, InvalidReviewException {
		return new ResponseEntity<>(pharmacyReviewService.findOneByPatientAndPharmacyId(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/medicine/{id}")
	public ResponseEntity<Integer> findMedicineScore(@PathVariable Long id) throws InvalidScoreException, InvalidReviewException {
		return new ResponseEntity<>(medicineReviewService.findOneByPatientAndMedicineId(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/employee/{id}")
	public ResponseEntity<Integer> findEmployeeScore(@PathVariable Long id) throws InvalidScoreException, InvalidReviewException {
		return new ResponseEntity<>(employeeReviewService.findOneByPatientAndEmployeeId(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/pharmacy")
	public ResponseEntity<PharmacyReview> reviewPharmacy(@RequestBody PharmacyReviewCreateDTO dto) throws InvalidScoreException, InvalidReviewException {
		return new ResponseEntity<>(pharmacyReviewService.save(dto), HttpStatus.OK);
	}
	
	@PostMapping(value = "/medicine")
	public ResponseEntity<MedicineReview> reviewPharmacy(@RequestBody MedicineReviewCreateDTO dto) throws InvalidScoreException, InvalidReviewException {
		return new ResponseEntity<>(medicineReviewService.save(dto), HttpStatus.OK);
	}
	
	@PostMapping(value = "/employee")
	public ResponseEntity<EmployeeReview> reviewPharmacy(@RequestBody EmployeeReviewCreateDTO dto) throws InvalidScoreException, InvalidReviewException {
		System.out.println(dto.getEmployeeId());
		System.out.println(dto.getScore());
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		return new ResponseEntity<>(employeeReviewService.save(dto), HttpStatus.OK);
	}
}
