package internet.software.architectures.team31.isapharmacy.domain.pharmacy;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;

@Entity
@Table(name="pricelist_item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
public abstract class PricelistItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private DateRange interval;
	
	@Column
	private Double price;
	
	@Column
	private PriceListItemStatus status;

	public PricelistItem(Long id, DateRange interval, Double price, PriceListItemStatus status) {
		super();
		this.id = id;
		this.interval = interval;
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
