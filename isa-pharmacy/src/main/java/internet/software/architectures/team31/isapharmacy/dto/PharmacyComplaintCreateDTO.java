package internet.software.architectures.team31.isapharmacy.dto;

public class PharmacyComplaintCreateDTO {

	private Long pharmacyId;
	private String complaintText;
	
	public PharmacyComplaintCreateDTO() {
		super();
	}

	public PharmacyComplaintCreateDTO(Long pharmacyId, String complaintText) {
		super();
		this.pharmacyId = pharmacyId;
		this.complaintText = complaintText;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public String getComplaintText() {
		return complaintText;
	}

	public void setComplaintText(String complaintText) {
		this.complaintText = complaintText;
	}
}
