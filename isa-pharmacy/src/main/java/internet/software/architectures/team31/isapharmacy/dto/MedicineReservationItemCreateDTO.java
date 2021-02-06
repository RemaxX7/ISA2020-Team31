package internet.software.architectures.team31.isapharmacy.dto;

public class MedicineReservationItemCreateDTO {

	private Long medicineId;
	private Integer quantity;
	
	public MedicineReservationItemCreateDTO() {
		super();
	}

	public MedicineReservationItemCreateDTO(Long medicineId, Integer quantity) {
		super();
		this.medicineId = medicineId;
		this.quantity = quantity;
	}

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
