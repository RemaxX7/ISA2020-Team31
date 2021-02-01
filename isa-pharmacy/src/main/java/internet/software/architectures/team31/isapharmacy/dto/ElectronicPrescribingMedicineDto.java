package internet.software.architectures.team31.isapharmacy.dto;

public class ElectronicPrescribingMedicineDto {

	private Long id;
	private String name;
	private int quantity;
	
	public ElectronicPrescribingMedicineDto() {
		
	}
	
	public ElectronicPrescribingMedicineDto(Long id, String name, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
