package internet.software.architectures.team31.isapharmacy.domain.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@DiscriminatorValue("Employee")
public class Employee extends User {
	
	private static final long serialVersionUID = -210188442140883409L;

	@Column(name="examined_patients_list")
	@ElementCollection(targetClass=Patient.class)
	private List<Patient> examinedPatientsList;
	
	@Column(name = "employee_type")
	private EmployeeType employeeType;

	public Employee() {
		super();
	}
	
	public Employee(List<Patient> examinedPatientsList, EmployeeType employeeType) {
		super();
		this.examinedPatientsList = examinedPatientsList;
		this.employeeType = employeeType;
	}

	public List<Patient> getExaminedPatientsList() {
		return examinedPatientsList;
	}

	public void setExaminedPatientsList(List<Patient> examinedPatientsList) {
		this.examinedPatientsList = examinedPatientsList;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
}
