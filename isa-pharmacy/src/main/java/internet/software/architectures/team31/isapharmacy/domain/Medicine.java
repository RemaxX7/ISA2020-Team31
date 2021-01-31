package internet.software.architectures.team31.isapharmacy.domain;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class Medicine {

	private Long id;
	
	@NotBlank(message = "Medicine name is mandatory")
	private String name;	
	
	@NotEmpty(message = "Medicine type is mandatory")
	private MedicineType type;
	
	@NotEmpty(message = "Medicine shape is mandatory")
	private MedicineShape shape;
	
	@NotBlank(message = "Medicine composition is mandatory")
	private String composition;
	
	@NotBlank(message = "Medicine manufacturer is mandatory")
	private String manufacturer;
	
	@NotEmpty(message = "Issuing mode for medicine is mandatory")
	private IssuingMode issuing;
	
	@NotEmpty(message = "Medicine must have at least one replacement medicine")
	private List<String> replacementMedicineCodes;
	
	private String additionalNotes;
	
	public Medicine() {
		
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

	public List<String> getReplacementMedicineCodes() {
		return replacementMedicineCodes;
	}

	public void setReplacementMedicineCodes(List<String> replacementMedicineCodes) {
		this.replacementMedicineCodes = replacementMedicineCodes;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}
	
}
