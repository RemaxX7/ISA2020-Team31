package internet.software.architectures.team31.isapharmacy.domain.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public abstract class Employee extends User {
	
	private static final long serialVersionUID = 2757475965342832965L;
	
	@Column(name="examined_patients_list")
	@ElementCollection(targetClass=Patient.class)
	private List<Patient> examinedPatientsList;

	public Employee() {
		super();
	}
	
	public Employee(List<Patient> examinedPatientsList) {
		super();
		this.examinedPatientsList = examinedPatientsList;
	}

	public List<Patient> getExaminedPatientsList() {
		return examinedPatientsList;
	}

	public void setExaminedPatientsList(List<Patient> examinedPatientsList) {
		this.examinedPatientsList = examinedPatientsList;
	}
}
