package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.patient.EmployeeReview;

public interface EmployeeReviewRepository extends JpaRepository<EmployeeReview, Long> {

	Collection<EmployeeReview> findAllByEmployeeId(Long id);
	EmployeeReview findOneByPatientIdAndEmployeeId(Long patientId, Long employeeId);
}
