package internet.software.architectures.team31.isapharmacy.dto;

import internet.software.architectures.team31.isapharmacy.domain.users.Patient;

public class PatientProfileDTO {

	private Long id;
	private String name;
	private String surname;
	private String phoneNumber;
	private String street;
	private String number;
	private Double latitude;
	private Double longitude;
	private Long cityId;
	private Long countryId;
	
	public PatientProfileDTO() {
		super();
	}

	public PatientProfileDTO(Long id, String name, String surname, String phoneNumber, String street, String number,
			Double latitude, Double longitude, Long cityId, Long countryId) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.number = number;
		this.latitude = latitude;
		this.longitude = longitude;
		this.cityId = cityId;
		this.countryId = countryId;
	}
	
	public PatientProfileDTO(Patient patient) {
		super();
		this.id = patient.getId();
		this.name = patient.getName();
		this.surname = patient.getSurname();
		this.phoneNumber = patient.getPhoneNumber();
		this.street = patient.getAddress().getStreet();
		this.number = patient.getAddress().getNumber();
		this.latitude = patient.getAddress().getLatitude();
		this.longitude = patient.getAddress().getLongitude();
		this.cityId = patient.getAddress().getCity().getId();
		this.countryId = patient.getAddress().getCity().getId();
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
}
