package internet.software.architectures.team31.isapharmacy.domain.patient;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.dto.PharmacyReviewCreateDTO;

@Entity
@DiscriminatorValue("Pharmacy")
public class PharmacyReview extends Review {

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Pharmacy pharmacy;

	public PharmacyReview() {
		super();
	}

	public PharmacyReview(Pharmacy pharmacy) {
		super();
		this.pharmacy = pharmacy;
	}
	
	public PharmacyReview(PharmacyReviewCreateDTO dto) {
		super();
		this.score = dto.getScore();
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
}
