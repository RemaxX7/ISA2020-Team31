package internet.software.architectures.team31.isapharmacy.domain.pharmacy;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;
import internet.software.architectures.team31.isapharmacy.dto.PriceListItemMedicineCreateDTO;

@Entity
@DiscriminatorValue("Medicine")
public class PriceListItemMedicine extends PricelistItem {
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Medicine medicine;

	public PriceListItemMedicine(Long id, DateRange interval, Double price, PriceListItemStatus status,
			Medicine medicine) {
		super(id, interval, price, status);
		this.medicine = medicine;
	}

	public PriceListItemMedicine() {
		super();
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	
}
