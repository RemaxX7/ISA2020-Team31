package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservation;
import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservationStatus;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReservationCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.CancelMedicineReservationException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;

public interface MedicineReservationService {

	MedicineReservation save(MedicineReservationCreateDTO dto) throws PenaltyException;
	MedicineReservation cancel(Long id) throws CancelMedicineReservationException;
	Collection<MedicineReservation> findAll();
	Collection<MedicineReservation> findAllByPatientId(Long id);
	Collection<MedicineReservation> findAllByPatientIdAndMedicineReservationStatus(Long patientId, MedicineReservationStatus status);
	MedicineReservation findById(Long id);
	boolean hasPatientPurchasedMedicineFromPharmacy(Long patientId, Long pharmacyId);
	boolean hasPatientPurchasedMedicine(Long patientId, Long medicineId);
	Collection<MedicineReservation> findById(String id,String uidn);
	MedicineReservation closeRequest(String code) throws CancelMedicineReservationException;
}
