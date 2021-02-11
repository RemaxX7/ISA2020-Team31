package internet.software.architectures.team31.isapharmacy.dto;

import java.time.LocalDate;

public class MedicineReservationCreateDTO {

	private Long pharmacyId;
	private LocalDate pickUpDate;
	private Long medicineId;
	
	public MedicineReservationCreateDTO() {
		super();
	}

	public MedicineReservationCreateDTO(Long pharmacyId, LocalDate pickUpDate,
			Long medicineId) {
		super();
		this.pharmacyId = pharmacyId;
		this.pickUpDate = pickUpDate;
		this.medicineId = medicineId;
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

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}
}
