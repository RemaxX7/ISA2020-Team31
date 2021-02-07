package internet.software.architectures.team31.isapharmacy.dto;

import java.time.LocalDate;
import java.util.List;

public class MedicineReservationCreateDTO {

	private Long patientId;
	private Long pharmacyId;
	private LocalDate pickUpDate;
	private List<MedicineReservationItemCreateDTO> medicineReservationItems;
	
	public MedicineReservationCreateDTO() {
		super();
	}

	public MedicineReservationCreateDTO(Long patientId, Long pharmacyId, LocalDate pickUpDate,
			List<MedicineReservationItemCreateDTO> medicineReservationItems) {
		super();
		this.patientId = patientId;
		this.pharmacyId = pharmacyId;
		this.pickUpDate = pickUpDate;
		this.medicineReservationItems = medicineReservationItems;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public LocalDate getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(LocalDate pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public List<MedicineReservationItemCreateDTO> getMedicineReservationItems() {
		return medicineReservationItems;
	}

	public void setMedicineReservationItems(List<MedicineReservationItemCreateDTO> medicineReservationItems) {
		this.medicineReservationItems = medicineReservationItems;
	}
}