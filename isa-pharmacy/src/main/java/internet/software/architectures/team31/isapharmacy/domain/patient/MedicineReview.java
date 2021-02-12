package internet.software.architectures.team31.isapharmacy.domain.patient;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReviewCreateDTO;

@Entity
@DiscriminatorValue("Medicine")
public class MedicineReview extends Review {

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Medicine medicine;

	public MedicineReview() {
		super();
	}

	public MedicineReview(Medicine medicine) {
		super();
		this.medicine = medicine;
	}

	public MedicineReview(MedicineReviewCreateDTO dto) {
		super();
		this.score = dto.getScore();
	}
	
	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
}
