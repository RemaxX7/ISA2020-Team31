package internet.software.architectures.team31.isapharmacy.dto;

public class SetPriceMedicineDTO {
	private Long pricelistId;
	private Long itemId;
	
	private PriceListItemMedicineCreateDTO dto;
	public SetPriceMedicineDTO() {
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
	public PriceListItemMedicineCreateDTO getDto() {
		return dto;
	}
	public void setDto(PriceListItemMedicineCreateDTO dto) {
		this.dto = dto;
	}
	
	
}
