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

import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReview;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReviewCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.InvalidReviewException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidScoreException;
import internet.software.architectures.team31.isapharmacy.service.MedicineReviewService;

@RestController
@RequestMapping(value = "api/reviews/medicine")
public class MedicineReviewController {

	@Autowired
	private MedicineReviewService medicineReviewService;
	
	@PostMapping(value = "/save")
	public ResponseEntity<MedicineReview> save(@RequestBody MedicineReviewCreateDTO dto) throws InvalidScoreException, InvalidReviewException {
		return new ResponseEntity<>(medicineReviewService.save(dto), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/score/{id}")
	public ResponseEntity<Double> getScore(@PathVariable Long id) {
		return new ResponseEntity<>(medicineReviewService.calculateMedicineScore(id), HttpStatus.OK);
	}
}
