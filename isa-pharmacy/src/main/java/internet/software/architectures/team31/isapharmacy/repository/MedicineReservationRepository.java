package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservation;
import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservationStatus;

public interface MedicineReservationRepository extends JpaRepository<MedicineReservation, Long> {

	Page<MedicineReservation> findAllByPatientId(Long id, Pageable pageable);
	Page<MedicineReservation> findAllByPatientIdAndMedicineReservationStatus(Long patientId, MedicineReservationStatus status, Pageable pageable);
	Collection<MedicineReservation> findAllByPatientIdAndMedicineReservationStatus(Long patientId, MedicineReservationStatus status);
	boolean existsByPatientIdAndPharmacyIdAndMedicineReservationStatus(Long patientId, Long pharmacyId, MedicineReservationStatus status);
}
