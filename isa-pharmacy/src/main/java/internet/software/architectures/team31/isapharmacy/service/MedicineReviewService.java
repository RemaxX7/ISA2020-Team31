package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReview;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReviewCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.InvalidReviewException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidScoreException;

public interface MedicineReviewService {

	MedicineReview save(MedicineReviewCreateDTO dto) throws InvalidScoreException, InvalidReviewException;
	Collection<MedicineReview> findAll();
	Double calculateMedicineScore(Long id);
}
