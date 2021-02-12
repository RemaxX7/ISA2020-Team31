package internet.software.architectures.team31.isapharmacy.domain.patient;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;
import internet.software.architectures.team31.isapharmacy.dto.CounselingCreateDTO;

@Entity
@DiscriminatorValue("Counseling")
public class Counseling extends Appointment {

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacist pharmacist;
	
	@Column
	private Integer points;

	public Counseling() {
		super();
	}

	public Counseling(Pharmacist pharmacist) {
		super();
		this.pharmacist = pharmacist;
	}
	
	public Counseling(CounselingCreateDTO dto) {
		super();
		this.dateRange = new DateRange(dto.getStartDateTime(), dto.getEndDateTime());
		this.price = dto.getPrice();
	}

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}
	
	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

}
