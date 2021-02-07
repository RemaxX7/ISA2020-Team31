package internet.software.architectures.team31.isapharmacy.domain.patient;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import internet.software.architectures.team31.isapharmacy.domain.users.Employee;
import internet.software.architectures.team31.isapharmacy.dto.EmployeeReviewCreateDTO;

@Entity
@DiscriminatorValue("Employee")
public class EmployeeReview extends Review {
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Employee employee;

	public EmployeeReview() {
		super();
	}

	public EmployeeReview(Employee employee) {
		super();
		this.employee = employee;
	}
	
	public EmployeeReview(EmployeeReviewCreateDTO dto) {
		super();
		this.score = dto.getScore();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
