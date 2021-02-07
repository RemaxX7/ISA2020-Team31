package internet.software.architectures.team31.isapharmacy.dto;

public class MedicineReviewCreateDTO {

	private Long medicineId;
	private Integer score;
	
	public MedicineReviewCreateDTO() {
		super();
	}
	
	public MedicineReviewCreateDTO(Long medicineId, Integer score) {
		super();
		this.medicineId = medicineId;
		this.score = score;
	}

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
