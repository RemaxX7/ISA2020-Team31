package internet.software.architectures.team31.isapharmacy.domain;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import internet.software.architectures.team31.isapharmacy.dto.ElectronicPrescribingMedicineDto;

//@Entity
public class ElectronicPrescription {
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Patient name is mandatory")
	//@Column(nullable = false)
	private String patientName;
	
	@NotBlank(message = "Patient surname is mandatory")
	//@Column(nullable = false)
	private String patientSurname;
	
	@NotEmpty(message = "Issue date may not be empty")
	//@Column(nullable = false)
	private Instant issueDate;
	
	@NotEmpty(message = "At least one medicine must be prescribed")
	//@Column(nullable = false)
	private Set<ElectronicPrescribingMedicineDto> prescribedMedicines;
	
	public ElectronicPrescription() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientSurname() {
		return patientSurname;
	}

	public void setPatientSurname(String patientSurname) {
		this.patientSurname = patientSurname;
	}

	public Instant getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Instant issueDate) {
		this.issueDate = issueDate;
	}

	public Set<ElectronicPrescribingMedicineDto> getPrescribedMedicines() {
		return prescribedMedicines;
	}

	public void setPrescribedMedicines(Set<ElectronicPrescribingMedicineDto> prescribedMedicines) {
		this.prescribedMedicines = prescribedMedicines;
	}

}
