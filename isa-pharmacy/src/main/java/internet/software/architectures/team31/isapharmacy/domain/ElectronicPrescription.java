package internet.software.architectures.team31.isapharmacy.domain;

import java.time.Instant;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import internet.software.architectures.team31.isapharmacy.dto.ElectronicPrescribingMedicineDto;

public class ElectronicPrescription {
	
	private Long id;
	
	@NotBlank(message = "Patient name is mandatory")
	private String patientName;
	
	@NotBlank(message = "Patient surname is mandatory")
	private String patientSurname;
	
	@NotEmpty(message = "Issue date may not be empty")
	private Instant issueDate;
	
	@NotEmpty(message = "At least one medicine must be prescribed")
	private List<ElectronicPrescribingMedicineDto> prescribedMedicines;
	
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

	public List<ElectronicPrescribingMedicineDto> getPrescribedMedicines() {
		return prescribedMedicines;
	}

	public void setPrescribedMedicines(List<ElectronicPrescribingMedicineDto> prescribedMedicines) {
		this.prescribedMedicines = prescribedMedicines;
	}
	
}
