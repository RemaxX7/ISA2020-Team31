package internet.software.architectures.team31.isapharmacy.domain.location;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Street name is mandatory")
	@Column(nullable = false)
	private String street;
	@NotBlank(message = "Street number is mandatory")
	@Column(nullable = false)
	private String number;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private City city;
	@NotEmpty(message = "Latitude is mandatory")
	@Column(nullable = false)
	private Double latitude;
	@NotEmpty(message = "Longitude is mandatory")
	@Column(nullable = false)
	private Double longitude;
	
	public Address() {
		super();
	}

	public Address(String street, String number, City city, Double latitude, Double longitude) {
		super();
		this.street = street;
		this.number = number;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
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
}
