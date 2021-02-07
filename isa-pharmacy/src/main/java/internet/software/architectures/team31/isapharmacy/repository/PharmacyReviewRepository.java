package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.patient.PharmacyReview;

public interface PharmacyReviewRepository extends JpaRepository<PharmacyReview, Long> {

	Collection<PharmacyReview> findAllByPharmacyId(Long id);
	PharmacyReview findOneByPatientIdAndPharmacyId(Long patientId, Long pharmacyId);
}
