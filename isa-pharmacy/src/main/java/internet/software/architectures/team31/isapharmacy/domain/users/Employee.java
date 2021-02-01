package internet.software.architectures.team31.isapharmacy.domain.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
@Entity
@DiscriminatorValue("Employee")
public class Employee extends User{
	
	@Column(name="examined_patients_list")
	@ElementCollection(targetClass=Patient.class)
	private List<Patient> examinedPatientsList;
	
	@Column(name = "employee_type")
	private EmployeeType employeeType;

	@Column(name="authentication_token",unique=true,nullable = false)
	@NotBlank(message = "Authentication token is mandatory")
	private String authenticationToken;
	
	public Employee(List<Patient> examinedPatientsList,
			@NotEmpty(message = "Employee type is mandatory") EmployeeType employeeType,
			@NotBlank(message = "authenticationToken is mandatory") String authenticationToken) {
		super();
		this.examinedPatientsList = examinedPatientsList;
		this.employeeType = employeeType;
		this.authenticationToken = authenticationToken;
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

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}
	
	
}
