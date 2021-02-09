package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReview;

public interface MedicineReviewRepository extends JpaRepository<MedicineReview, Long> {

	Collection<MedicineReview> findAllByMedicineId(Long id);
	MedicineReview findOneByPatientIdAndMedicineId(Long patientId, Long medicineId);
}
