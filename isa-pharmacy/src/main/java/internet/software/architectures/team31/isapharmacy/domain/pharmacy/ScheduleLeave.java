package internet.software.architectures.team31.isapharmacy.domain.pharmacy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import internet.software.architectures.team31.isapharmacy.domain.users.Employee;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;

@Entity
public class ScheduleLeave {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	@Embedded
	protected DateRange dateRange;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	protected Employee employee;
	@Column
	protected LeaveStatus status;
	
	public ScheduleLeave(Long id, DateRange dateRange, Employee employee, LeaveStatus status) {
		super();
		this.id = id;
		this.dateRange = dateRange;
		this.employee = employee;
		this.status = status;
	}

	public ScheduleLeave() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DateRange getDateRange() {
		return dateRange;
	}

	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LeaveStatus getStatus() {
		return status;
	}

	public void setStatus(LeaveStatus status) {
		this.status = status;
	}
	
	
}
