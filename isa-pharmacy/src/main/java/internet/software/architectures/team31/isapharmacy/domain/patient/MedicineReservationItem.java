package internet.software.architectures.team31.isapharmacy.domain.patient;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;

@Entity
@Table(name = "medicine_reservation_items")
public class MedicineReservationItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id", unique = true, nullable = false)
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Medicine medicine;
	@Column
	private Integer quantity;
	
	public MedicineReservationItem() {
		super();
	}

	public MedicineReservationItem(Long id, Medicine medicine, Integer quantity) {
		super();
		this.id = id;
		this.medicine = medicine;
		this.quantity = quantity;
	}
	
	public MedicineReservationItem(Medicine medicine, Integer quantity) {
		super();
		this.medicine = medicine;
		this.quantity = quantity;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
