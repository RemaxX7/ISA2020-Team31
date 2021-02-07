package internet.software.architectures.team31.isapharmacy.dto;

public class PharmacyComplaintCreateDTO {

	private Long patientId;
	private Long pharmacyId;
	private String complaintText;
	
	public PharmacyComplaintCreateDTO() {
		super();
	}

	public PharmacyComplaintCreateDTO(Long patientId, Long pharmacyId, String complaintText) {
		super();
		this.patientId = patientId;
		this.pharmacyId = pharmacyId;
		this.complaintText = complaintText;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
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
