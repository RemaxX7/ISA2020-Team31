package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.patient.PharmacyReview;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.dto.PharmacyReviewCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.InvalidReviewException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidScoreException;
import internet.software.architectures.team31.isapharmacy.repository.PharmacyReviewRepository;
import internet.software.architectures.team31.isapharmacy.service.AppointmentService;
import internet.software.architectures.team31.isapharmacy.service.MedicineReservationService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyReviewService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@Service
public class PharmacyReviewServiceImpl implements PharmacyReviewService {

	@Autowired
	private PharmacyReviewRepository pharmacyReviewRepository;
	@Autowired
	private PharmacyService pharmacyService;
	@Autowired
	private UserService userService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private MedicineReservationService medicineReservationService;

	@Override
	public PharmacyReview save(PharmacyReviewCreateDTO dto) throws InvalidScoreException, InvalidReviewException {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if(dto.getScore() < 1 || dto.getScore() > 5) {
			throw new InvalidScoreException("Review score cannot be less than 1, or greater than 5.");
		}
		if(!appointmentService.hasPatientVisitedPharmacy(patient.getId(), dto.getPharmacyId()) &&
				!medicineReservationService.hasPatientPurchasedMedicineFromPharmacy(patient.getId(), dto.getPharmacyId())) {
			//TODO: Add check for e-prescriptions
			throw new InvalidReviewException("You cannot rate this pharmacy.");
		}
		if(hasPatientReviewedPharmacy(patient.getId(), dto.getPharmacyId())) {
			return update(dto);
		}
		
		PharmacyReview review = new PharmacyReview(dto);
		review.setPharmacy(pharmacyService.findById(dto.getPharmacyId()));
		review.setPatient(patient);
		return pharmacyReviewRepository.save(review);
	}
	
	@Override
	public Collection<PharmacyReview> findAll() {
		return pharmacyReviewRepository.findAll();
	}

	@Override
	public Double calculatePharmacyScore(Long id) {
		Collection<PharmacyReview> reviews = pharmacyReviewRepository.findAllByPharmacyId(id);
		if(reviews.size() == 0) {
			return 0D;
		}
		Double score = 0D;
		for(PharmacyReview review : reviews) {
			score += review.getScore();
		}
		return score /= reviews.size();
	}

	private boolean hasPatientReviewedPharmacy(Long patientId, Long pharmacyId) {
		return pharmacyReviewRepository.findOneByPatientIdAndPharmacyId(patientId, pharmacyId) != null;
	}
	
	private PharmacyReview update(PharmacyReviewCreateDTO dto) {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		PharmacyReview review = pharmacyReviewRepository.findOneByPatientIdAndPharmacyId(patient.getId(), dto.getPharmacyId());
		review.setScore(dto.getScore());
		return pharmacyReviewRepository.save(review);
	}

	@Override
	public Integer findOneByPatientAndPharmacyId(Long pharmacyId) {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		PharmacyReview review = pharmacyReviewRepository.findOneByPatientIdAndPharmacyId(patient.getId(), pharmacyId);
		if(review == null) {
			return 0;
		} else {
			return review.getScore();
		}
	}
}
