package internet.software.architectures.team31.isapharmacy.domain.patient;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import internet.software.architectures.team31.isapharmacy.domain.users.Employee;
import internet.software.architectures.team31.isapharmacy.dto.EmployeeComplaintCreateDTO;

@Entity
@DiscriminatorValue("EmployeeComplaint")
public class EmployeeComplaint extends Complaint {

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Employee employee;

	public EmployeeComplaint() {
		super();
	}

	public EmployeeComplaint(Employee employee) {
		super();
		this.employee = employee;
	}
	
	public EmployeeComplaint(EmployeeComplaintCreateDTO dto) {
		super();
		this.complaintText = dto.getComplaintText();
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
