package internet.software.architectures.team31.isapharmacy.domain.pharmacy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;

@Entity
public class PricelistItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private DateRange interval;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Medicine medicine;
	
	@Column
	private Double price;
	
	@Column
	private PriceListItemStatus status;

	public PricelistItem(Long id, DateRange interval, Medicine medicine, Double price, PriceListItemStatus status) {
		super();
		this.id = id;
		this.interval = interval;
		this.medicine = medicine;
		this.price = price;
		this.status = status;
	}

	public PricelistItem() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DateRange getInterval() {
		return interval;
	}

	public void setInterval(DateRange interval) {
		this.interval = interval;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public PriceListItemStatus getStatus() {
		return status;
	}

	public void setStatus(PriceListItemStatus status) {
		this.status = status;
	}
	
	
}
