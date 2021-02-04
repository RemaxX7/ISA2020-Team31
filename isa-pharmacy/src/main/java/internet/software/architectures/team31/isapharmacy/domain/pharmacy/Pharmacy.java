package internet.software.architectures.team31.isapharmacy.domain.pharmacy;

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
import javax.validation.constraints.NotEmpty;

import internet.software.architectures.team31.isapharmacy.domain.location.Address;

@Entity
@Table(name = "pharmacies")
public class Pharmacy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="name")
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@NotEmpty(message = "Address is mandatory")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Address address;
	
	@Column(name="grade")
	private Integer rate;
	
	public Pharmacy() {
		super();
	}

	public Pharmacy(Long id, String name, Address address, Integer grade) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.rate = grade;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	
	
	//TODO: implement the rest of the class
}
