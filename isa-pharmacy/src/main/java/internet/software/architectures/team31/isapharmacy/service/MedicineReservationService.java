package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservation;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReservationCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.CancelMedicineReservationException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;

public interface MedicineReservationService {

	MedicineReservation save(MedicineReservationCreateDTO dto) throws PenaltyException;
	MedicineReservation cancel(Long id) throws CancelMedicineReservationException;
	Collection<MedicineReservation> findAll();
	Collection<MedicineReservation> findAllByPatientId(Long id);
	MedicineReservation findById(Long id);
	boolean hasPatientPurchasedMedicineFromPharmacy(Long patientId, Long pharmacyId);
}
