package internet.software.architectures.team31.isapharmacy.domain.eprescription;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class ElectronicPrescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Patient name is mandatory")
	@Column(nullable = false)
	private String patientName;
	
	@NotBlank(message = "Patient surname is mandatory")
	@Column(nullable = false)
	private String patientSurname;
	
	@NotEmpty(message = "Issue date may not be empty")
	@Column(nullable = false)
	private Instant issueDate;
	
	@NotEmpty(message = "At least one medicine must be prescribed")
	@OneToMany(mappedBy = "medicine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ElectronicPrescribingMedicine> prescribedMedicines = new HashSet<ElectronicPrescribingMedicine>();
	
	public ElectronicPrescription() {
		
	}
	
	public ElectronicPrescription(Long id, @NotBlank(message = "Patient name is mandatory") String patientName,
			@NotBlank(message = "Patient surname is mandatory") String patientSurname,
			@NotEmpty(message = "Issue date may not be empty") Instant issueDate) {
		super();
		this.id = id;
		this.patientName = patientName;
		this.patientSurname = patientSurname;
		this.issueDate = issueDate;
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

	public Set<ElectronicPrescribingMedicine> getPrescribedMedicines() {
		return prescribedMedicines;
	}

	public void setPrescribedMedicines(Set<ElectronicPrescribingMedicine> prescribedMedicines) {
		this.prescribedMedicines = prescribedMedicines;
	}

}
