package internet.software.architectures.team31.isapharmacy.domain;

import java.util.List;

public class Medicine {

	private Long id;
	private String name;	
	private MedicineType type;
	private MedicineShape shape;
	private String composition;
	private String manufacturer;
	private IssuingMode issuing;
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
