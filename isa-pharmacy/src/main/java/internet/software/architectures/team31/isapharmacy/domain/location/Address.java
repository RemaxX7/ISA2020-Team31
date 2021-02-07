package internet.software.architectures.team31.isapharmacy.domain.location;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import internet.software.architectures.team31.isapharmacy.dto.AddressCreateDTO;

@Entity
@Table(name = "addresses")
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
	@NotNull(message = "Latitude is mandatory")
	@Column(nullable = false)
	private Double latitude;
	@NotNull(message = "Longitude is mandatory")
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
	
	public Address(AddressCreateDTO dto) {
		super();
		this.street = dto.getStreet();
		this.number = dto.getNumber();
		this.latitude = dto.getLatitude();
		this.longitude = dto.getLatitude();
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
	
	@Override
	public String toString() {
		return this.street + " " + this.number + ", " + this.city.getName();
	}
}
