package internet.software.architectures.team31.isapharmacy.domain.medicine;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Medicine name is mandatory")
	@Column(nullable = false)
	private String name;	
	
	@NotEmpty(message = "Medicine type is mandatory")
	@Column(nullable = false)
	private MedicineType type;
	
	@NotEmpty(message = "Medicine shape is mandatory")
	@Column(nullable = false)
	private MedicineShape shape;
	
	@NotBlank(message = "Medicine composition is mandatory")
	@Column(nullable = false)
	private String composition;
	
	@NotBlank(message = "Medicine manufacturer is mandatory")
	@Column(nullable = false)
	private String manufacturer;
	
	@NotEmpty(message = "Issuing mode for medicine is mandatory")
	@Column(nullable = false)
	private IssuingMode issuing;
	
	@Column
	@ElementCollection(targetClass=Long.class)
	private Set<Long> replacementMedicineCodes;
	
	@Column
	private String additionalNotes;
	
	public Medicine() {
		
	}

	public Medicine(Long id, @NotBlank(message = "Medicine name is mandatory") String name,
			@NotEmpty(message = "Medicine type is mandatory") MedicineType type,
			@NotEmpty(message = "Medicine shape is mandatory") MedicineShape shape,
			@NotBlank(message = "Medicine composition is mandatory") String composition,
			@NotBlank(message = "Medicine manufacturer is mandatory") String manufacturer,
			@NotEmpty(message = "Issuing mode for medicine is mandatory") IssuingMode issuing,
			Set<Long> replacementMedicineCodes, String additionalNotes) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.shape = shape;
		this.composition = composition;
		this.manufacturer = manufacturer;
		this.issuing = issuing;
		this.replacementMedicineCodes = replacementMedicineCodes;
		this.additionalNotes = additionalNotes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MedicineType getType() {
		return type;
	}

	public void setType(MedicineType type) {
		this.type = type;
	}

	public MedicineShape getShape() {
		return shape;
	}

	public void setShape(MedicineShape shape) {
		this.shape = shape;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public IssuingMode getIssuing() {
		return issuing;
	}

	public void setIssuing(IssuingMode issuing) {
		this.issuing = issuing;
	}

	public Set<Long> getReplacementMedicineCodes() {
		return replacementMedicineCodes;
	}

	public void setReplacementMedicineCodes(Set<Long> replacementMedicineCodes) {
		this.replacementMedicineCodes = replacementMedicineCodes;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}
	
}
