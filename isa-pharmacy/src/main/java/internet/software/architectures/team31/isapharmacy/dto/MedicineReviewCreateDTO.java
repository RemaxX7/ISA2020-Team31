package internet.software.architectures.team31.isapharmacy.dto;

public class MedicineReviewCreateDTO {

	private Long patientId;
	private Long medicineId;
	private Integer score;
	
	public MedicineReviewCreateDTO() {
		super();
	}
	
	public MedicineReviewCreateDTO(Long patientId, Long medicineId, Integer score) {
		super();
		this.patientId = patientId;
		this.medicineId = medicineId;
		this.score = score;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
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
