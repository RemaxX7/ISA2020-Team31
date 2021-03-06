package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservation;
import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservationStatus;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReservationCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReservationViewDTO;
import internet.software.architectures.team31.isapharmacy.exception.CancelMedicineReservationException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;

public interface MedicineReservationService {

	MedicineReservation save(MedicineReservationCreateDTO dto) throws PenaltyException;
	MedicineReservation cancel(Long id) throws CancelMedicineReservationException;
	Collection<MedicineReservation> findAll();
	Page<MedicineReservationViewDTO> findAllByPatient(Pageable pageable);
	Page<MedicineReservationViewDTO> findAllByPatientAndMedicineReservationStatus(MedicineReservationStatus status, Pageable pageable);
	MedicineReservation findById(Long id);
	boolean hasPatientPurchasedMedicineFromPharmacy(Long patientId, Long pharmacyId);
	boolean hasPatientPurchasedMedicine(Long patientId, Long medicineId);
	Collection<MedicineReservation> findById(String id,String uidn) throws CancelMedicineReservationException;
	MedicineReservation closeRequest(String code) throws CancelMedicineReservationException;
}
