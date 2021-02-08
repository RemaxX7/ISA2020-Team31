package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import internet.software.architectures.team31.isapharmacy.domain.patient.PharmacyReview;
import internet.software.architectures.team31.isapharmacy.dto.PharmacyReviewCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.InvalidReviewException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidScoreException;

public interface PharmacyReviewService {

	PharmacyReview save(PharmacyReviewCreateDTO dto) throws InvalidScoreException, InvalidReviewException;
	Collection<PharmacyReview> findAll();
	Double calculatePharmacyScore(Long id);
}
