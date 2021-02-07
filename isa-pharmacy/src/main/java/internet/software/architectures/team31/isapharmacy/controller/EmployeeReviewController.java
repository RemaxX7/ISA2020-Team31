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
import internet.software.architectures.team31.isapharmacy.dto.EmployeeReviewCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.InvalidReviewException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidScoreException;
import internet.software.architectures.team31.isapharmacy.service.EmployeeReviewService;

@RestController
@RequestMapping(value = "api/reviews/employee")
public class EmployeeReviewController {

	@Autowired
	private EmployeeReviewService employeeReviewService;
	
	@PostMapping(value = "/save")
	public ResponseEntity<EmployeeReview> save(@RequestBody EmployeeReviewCreateDTO dto) throws InvalidScoreException, InvalidReviewException {
		return new ResponseEntity<>(employeeReviewService.save(dto), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/score/{id}")
	public ResponseEntity<Double> getScore(@PathVariable Long id) {
		return new ResponseEntity<>(employeeReviewService.calculateEmployeeScore(id), HttpStatus.OK);
	}
}
