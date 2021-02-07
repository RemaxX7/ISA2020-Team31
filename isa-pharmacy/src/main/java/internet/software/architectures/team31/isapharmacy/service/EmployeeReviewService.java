package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import internet.software.architectures.team31.isapharmacy.domain.patient.EmployeeReview;
import internet.software.architectures.team31.isapharmacy.dto.EmployeeReviewCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.InvalidReviewException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidScoreException;

public interface EmployeeReviewService {

	EmployeeReview save(EmployeeReviewCreateDTO dto) throws InvalidScoreException, InvalidReviewException;
	Collection<EmployeeReview> findAll();
	Double calculateEmployeeScore(Long id);
}
