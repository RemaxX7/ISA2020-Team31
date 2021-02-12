package internet.software.architectures.team31.isapharmacy.domain.pharmacy;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "pricelists")
public class Pricelist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@OneToOne
	private Pharmacy pharmacy;
	
	@Column
	@ElementCollection(targetClass=PricelistItem.class)
	private List<PricelistItem> items;

	public Pricelist(Long id, Pharmacy pharmacy, List<PricelistItem> items) {
		super();
		this.id = id;
		this.pharmacy = pharmacy;
		this.items = items;
	}

	public Pricelist() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public List<PricelistItem> getItems() {
		return items;
	}

	public void setItems(List<PricelistItem> items) {
		this.items = items;
	}
	

	

	
}
