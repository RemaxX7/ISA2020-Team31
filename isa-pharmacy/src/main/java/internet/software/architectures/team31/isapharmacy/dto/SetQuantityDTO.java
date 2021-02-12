package internet.software.architectures.team31.isapharmacy.dto;

public class SetQuantityDTO {
	private Long itemId;
	private Double quantity;
	
	public SetQuantityDTO() {
		super();
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	

}
