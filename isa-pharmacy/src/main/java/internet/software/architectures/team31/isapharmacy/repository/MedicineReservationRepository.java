package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservation;
import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservationStatus;

public interface MedicineReservationRepository extends JpaRepository<MedicineReservation, Long> {

	Collection<MedicineReservation> findAllByPatientId(Long id);
	Collection<MedicineReservation> findAllByPatientIdAndMedicineReservationStatus(Long patientId, MedicineReservationStatus status);
	MedicineReservation findOneByPatientIdAndPharmacyIdAndMedicineReservationStatus(Long patientId, Long pharmacyId, MedicineReservationStatus status);
}
