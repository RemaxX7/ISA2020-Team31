package internet.software.architectures.team31.isapharmacy.dto;

import internet.software.architectures.team31.isapharmacy.domain.location.Address;

public class AddressViewDTO {

	private Long id;
	private String street;
	private String number;
	private String city;
	
	public AddressViewDTO() {
		super();
	}

	public AddressViewDTO(Long id, String street, String number, String city) {
		super();
		this.id = id;
		this.street = street;
		this.number = number;
		this.city = city;
	}
	
	public AddressViewDTO(Address address) {
		super();
		this.id = address.getId();
		this.street = address.getStreet();
		this.number = address.getNumber();
		this.city = address.getCity().getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
