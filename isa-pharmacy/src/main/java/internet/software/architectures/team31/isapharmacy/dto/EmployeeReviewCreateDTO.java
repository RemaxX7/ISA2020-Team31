package internet.software.architectures.team31.isapharmacy.dto;

public class EmployeeReviewCreateDTO {

	private Long employeeId;
	private Integer score;
	
	public EmployeeReviewCreateDTO() {
		super();
	}

	public EmployeeReviewCreateDTO(Long employeeId, Integer score) {
		super();
		this.employeeId = employeeId;
		this.score = score;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
