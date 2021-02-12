package internet.software.architectures.team31.isapharmacy.dto;

import java.time.LocalDateTime;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.PriceListItemMedicine;

public class PriceListItemMedicineCreateDTO {
	private Long medicineId;
	private LocalDateTime startDateTime;
	private Double price;
	
	public PriceListItemMedicineCreateDTO() {
		super();
	}
	public PriceListItemMedicineCreateDTO(PriceListItemMedicine item) {
		super();
		this.medicineId=item.getMedicine().getId();
		this.startDateTime=item.getInterval().getStartDateTime();
		this.price=item.getPrice();
	}


	public PriceListItemMedicineCreateDTO(Long medicineId, LocalDateTime startDateTime, Double price) {
		super();
		this.medicineId = medicineId;
		this.startDateTime = startDateTime;
		this.price = price;
	}
	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
