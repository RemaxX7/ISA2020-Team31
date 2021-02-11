package internet.software.architectures.team31.isapharmacy.dto;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;

public class MedicineViewDTO {

	private Long id;
	private String name;
	private String type;
	private Double rate;
	
	public MedicineViewDTO() {
		super();
	}

	public MedicineViewDTO(Long id, String name, String type, Double rate) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.rate = rate;
	}
	
	public MedicineViewDTO(Medicine medicine) {
		super();
		this.id = medicine.getId();
		this.name = medicine.getName();
		this.type = medicine.getType().toString();
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
}
