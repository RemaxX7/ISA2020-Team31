package internet.software.architectures.team31.isapharmacy.dto;

public class PharmacyReviewCreateDTO {

	private Long patientId;
	private Long pharmacyId;
	private Integer score;
	
	public PharmacyReviewCreateDTO() {
		super();
	}

	public PharmacyReviewCreateDTO(Long patientId, Long pharmacyId, Integer score) {
		super();
		this.patientId = patientId;
		this.pharmacyId = pharmacyId;
		this.score = score;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
