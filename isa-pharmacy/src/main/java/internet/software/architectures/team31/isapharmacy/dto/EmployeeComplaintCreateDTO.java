package internet.software.architectures.team31.isapharmacy.dto;

public class EmployeeComplaintCreateDTO {

	private Long employeeId;
	private String complaintText;
	
	public EmployeeComplaintCreateDTO() {
		super();
	}

	public Long getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getComplaintText() {
		return complaintText;
	}
	
	public void setComplaintText(String complaintText) {
		this.complaintText = complaintText;
	}
}
