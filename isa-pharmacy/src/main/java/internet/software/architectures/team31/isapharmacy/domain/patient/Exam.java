package internet.software.architectures.team31.isapharmacy.domain.patient;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import internet.software.architectures.team31.isapharmacy.domain.users.Dermatologist;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;
import internet.software.architectures.team31.isapharmacy.dto.ExamCreateDTO;

@Entity
@DiscriminatorValue("Exam")
public class Exam extends Appointment {

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Dermatologist dermatologist;

	@Column
	private Integer points;
	
	public Exam() {
		super();
	}

	public Exam(Dermatologist dermatologist) {
		super();
		this.dermatologist = dermatologist;
	}
	
	public Exam(ExamCreateDTO dto) {
		super();
		this.dateRange = new DateRange(dto.getStartDateTime(), dto.getEndDateTime());
		this.price = dto.getPrice();
	}

	public Dermatologist getDermatologist() {
		return dermatologist;
	}

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}
	
	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

}
