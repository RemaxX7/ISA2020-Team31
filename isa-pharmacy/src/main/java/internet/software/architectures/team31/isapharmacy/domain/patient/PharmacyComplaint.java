package internet.software.architectures.team31.isapharmacy.domain.patient;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.dto.PharmacyComplaintCreateDTO;

@Entity
@DiscriminatorValue("PharmacyComplaint")
public class PharmacyComplaint extends Complaint {

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;

	public PharmacyComplaint() {
		super();
	}

	public PharmacyComplaint(Pharmacy pharmacy) {
		super();
		this.pharmacy = pharmacy;
	}
	
	public PharmacyComplaint(PharmacyComplaintCreateDTO dto) {
		super();
		this.complaintText = dto.getComplaintText();
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
}
