package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.patient.EmployeeReview;
import internet.software.architectures.team31.isapharmacy.domain.users.Employee;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.dto.EmployeeReviewCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.InvalidReviewException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidScoreException;
import internet.software.architectures.team31.isapharmacy.repository.EmployeeReviewRepository;
import internet.software.architectures.team31.isapharmacy.service.CounselingService;
import internet.software.architectures.team31.isapharmacy.service.EmployeeReviewService;
import internet.software.architectures.team31.isapharmacy.service.ExamService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@Service
public class EmployeeReviewServiceImpl implements EmployeeReviewService {

	@Autowired
	private EmployeeReviewRepository employeeReviewRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ExamService examService;
	@Autowired
	private CounselingService counselingService;

	@Override
	public EmployeeReview save(EmployeeReviewCreateDTO dto) throws InvalidScoreException, InvalidReviewException {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if(dto.getScore() < 1 || dto.getScore() > 5) {
			throw new InvalidScoreException("Review score cannot be less than 1, or greater than 5.");
		}
		if(!examService.hasPatientVisitedDermatologist(patient.getId(), dto.getEmployeeId()) &&
				!counselingService.hasPatientVisitedPharmacist(patient.getId(), dto.getEmployeeId())) {
			throw new InvalidReviewException("You cannot review this employee.");
		}
		if(hasPatientReviewedEmployee(patient.getId(), dto.getEmployeeId())) {
			return update(dto);
		}
		
		EmployeeReview review = new EmployeeReview(dto);
		review.setEmployee((Employee) userService.findById(dto.getEmployeeId()));
		review.setPatient(patient);
		return employeeReviewRepository.save(review);
	}

	@Override
	public Collection<EmployeeReview> findAll() {
		return employeeReviewRepository.findAll();
	}

	@Override
	public Double calculateEmployeeScore(Long id) {
		Collection<EmployeeReview> reviews = employeeReviewRepository.findAllByEmployeeId(id);
		if(reviews.size() == 0) {
			return 0D;
		}
		Double score = 0D;
		for(EmployeeReview review : reviews) {
			score += review.getScore();
		}
		return score /= reviews.size();
	}
	
	private boolean hasPatientReviewedEmployee(Long patientId, Long employeeId) {
		return employeeReviewRepository.findOneByPatientIdAndEmployeeId(patientId, employeeId) != null;
	}
	
	private EmployeeReview update(EmployeeReviewCreateDTO dto) {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		EmployeeReview review = employeeReviewRepository.findOneByPatientIdAndEmployeeId(patient.getId(), dto.getEmployeeId());
		review.setScore(dto.getScore());
		return employeeReviewRepository.save(review);
	}
}
