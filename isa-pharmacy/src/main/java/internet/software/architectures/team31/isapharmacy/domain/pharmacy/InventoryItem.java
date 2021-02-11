package internet.software.architectures.team31.isapharmacy.domain.pharmacy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;

@Entity
public class InventoryItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Medicine medicine;
	
	@Column
	private Double quantity;

	public InventoryItem(Long id, Medicine medicine, Double quantity) {
		super();
		this.id = id;
		this.medicine = medicine;
		this.quantity = quantity;
	}

	public InventoryItem() {
		super();
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

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	
}
