package internet.software.architectures.team31.isapharmacy.dto;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;

public class PharmacyViewDTO {

	private Long id;
	private String name;
	private Double rate;
	private AddressViewDTO address;
	
	public PharmacyViewDTO() {
		super();
	}

	public PharmacyViewDTO(Long id, String name, Double rate, AddressViewDTO address) {
		super();
		this.id = id;
		this.name = name;
		this.rate = rate;
		this.address = address;
	}
	
	public PharmacyViewDTO(Pharmacy pharmacy) {
		super();
		this.id = pharmacy.getId();
		this.name = pharmacy.getName();
		this.address = new AddressViewDTO(pharmacy.getAddress());
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

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public AddressViewDTO getAddress() {
		return address;
	}

	public void setAddress(AddressViewDTO address) {
		this.address = address;
	}
}
