package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReview;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReviewCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.InvalidReviewException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidScoreException;
import internet.software.architectures.team31.isapharmacy.repository.MedicineReviewRepository;
import internet.software.architectures.team31.isapharmacy.service.MedicineReservationService;
import internet.software.architectures.team31.isapharmacy.service.MedicineReviewService;
import internet.software.architectures.team31.isapharmacy.service.MedicineService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@Service
public class MedicineReviewServiceImpl implements MedicineReviewService {

	@Autowired
	private MedicineReviewRepository medicineReviewRepository;
	@Autowired
	private MedicineService medicineService;
	@Autowired
	private UserService userService;
	@Autowired
	private MedicineReservationService medicineReservationService;
	
	@Override
	public MedicineReview save(MedicineReviewCreateDTO dto) throws InvalidScoreException, InvalidReviewException {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if(dto.getScore() < 1 || dto.getScore() > 5) {
			throw new InvalidScoreException("Review score cannot be less than 1, or greater than 5.");
		}
		if(!medicineReservationService.hasPatientPurchasedMedicine(patient.getId(), dto.getMedicineId())) {
			//TODO: Add check for e-prescriptions
			throw new InvalidReviewException("You cannot review this medicine.");
		}
		if(hasPatientReviewedMedicine(patient.getId(), dto.getMedicineId())) {
			return update(dto);
		}
		
		MedicineReview review = new MedicineReview(dto);
		review.setMedicine(medicineService.findById(dto.getMedicineId()));
		review.setPatient(patient);
		return medicineReviewRepository.save(review);
	}
	
	@Override
	public Collection<MedicineReview> findAll() {
		return medicineReviewRepository.findAll();
	}
	
	@Override
	public Double calculateMedicineScore(Long id) {
		Collection<MedicineReview> reviews = medicineReviewRepository.findAllByMedicineId(id);
		if(reviews.size() == 0) {
			return 0D;
		}
		Double score = 0D;
		for(MedicineReview review : reviews) {
			score += review.getScore();
		}
		return score /= reviews.size();
	}
	
	private boolean hasPatientReviewedMedicine(Long patientId, Long medicineId) {
		return medicineReviewRepository.findOneByPatientIdAndMedicineId(patientId, medicineId) != null;
	}
	
	private MedicineReview update(MedicineReviewCreateDTO dto) {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		MedicineReview review = medicineReviewRepository.findOneByPatientIdAndMedicineId(patient.getId(), dto.getMedicineId());
		review.setScore(dto.getScore());
		return medicineReviewRepository.save(review);
	}
}
