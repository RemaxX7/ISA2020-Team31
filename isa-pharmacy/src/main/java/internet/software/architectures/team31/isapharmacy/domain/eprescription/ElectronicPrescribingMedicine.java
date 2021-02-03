package internet.software.architectures.team31.isapharmacy.domain.eprescription;

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

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;

@Entity
public class ElectronicPrescribingMedicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Medicine id is mandatory")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Medicine medicine;
	
	@NotBlank(message = "Medicine name is mandatory")
	@Column(nullable = false)
	private String name;
	
	@NotEmpty(message = "Medicine quantity is mandatory")
	@Column(nullable = false)
	private int quantity;
	
	public ElectronicPrescribingMedicine() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
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
