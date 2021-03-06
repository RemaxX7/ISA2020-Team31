package internet.software.architectures.team31.isapharmacy.domain.pharmacy;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@NotEmpty(message = "Address is mandatory")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Address address;
	
	@Column(name="rate")
	private Double rate;
	
	@Column
	@ElementCollection(targetClass=InventoryItem.class)
	private List<InventoryItem> inventory;
	
	public Pharmacy() {
		super();
	}

	public Pharmacy(Long id, String name, Address address, Double rate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.rate = rate;
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

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public List<InventoryItem> getInventory() {
		return inventory;
	}

	public void setInventory(List<InventoryItem> inventory) {
		this.inventory = inventory;
	}

	
	
	//TODO: implement the rest of the class
}
