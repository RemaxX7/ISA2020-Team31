package internet.software.architectures.team31.isapharmacy.dto;

public class SetPriceAppointmentDTO {
	private Long pricelistId;
	private Long itemId;
	
	private PriceListItemAppointmentCreateDTO dto;

	public SetPriceAppointmentDTO() {
		super();
	}

	public Long getPricelistId() {
		return pricelistId;
	}

	public void setPricelistId(Long pricelistId) {
		this.pricelistId = pricelistId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public PriceListItemAppointmentCreateDTO getDto() {
		return dto;
	}

	public void setDto(PriceListItemAppointmentCreateDTO dto) {
		this.dto = dto;
	}
	
	

}
