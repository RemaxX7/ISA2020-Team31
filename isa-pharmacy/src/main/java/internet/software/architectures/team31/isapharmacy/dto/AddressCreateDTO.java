package internet.software.architectures.team31.isapharmacy.dto;

public class AddressCreateDTO {

	private String street;
	private String number;
	private Double latitude;
	private Double longitude;
	private Long countryId;
	private Long cityId;
	
	public AddressCreateDTO() {
		super();
	}

	public AddressCreateDTO(String street, String number, Double latitude, Double longitude, Long countryId,
			Long cityId) {
		super();
		this.street = street;
		this.number = number;
		this.latitude = latitude;
		this.longitude = longitude;
		this.countryId = countryId;
		this.cityId = cityId;
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

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
}
