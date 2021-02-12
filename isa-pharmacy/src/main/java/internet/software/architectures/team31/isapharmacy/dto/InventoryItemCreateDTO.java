package internet.software.architectures.team31.isapharmacy.dto;


public class InventoryItemCreateDTO {

	private Long pharmacyId;
	
	private Long medicineId;
	
	private Double quantity;

	public InventoryItemCreateDTO(Long pharmacyId, Long medicineId, Double quantity) {
		super();
		this.pharmacyId = pharmacyId;
		this.medicineId = medicineId;
		this.quantity = quantity;
	}

	public InventoryItemCreateDTO() {
		super();
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	
}
