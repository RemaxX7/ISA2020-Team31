package internet.software.architectures.team31.isapharmacy.dto;

public class PharmacyReviewCreateDTO {

	private Long pharmacyId;
	private Integer score;
	
	public PharmacyReviewCreateDTO() {
		super();
	}

	public PharmacyReviewCreateDTO(Long pharmacyId, Integer score) {
		super();
		this.pharmacyId = pharmacyId;
		this.score = score;
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
