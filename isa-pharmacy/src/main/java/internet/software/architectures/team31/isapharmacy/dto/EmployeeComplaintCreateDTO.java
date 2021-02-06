package internet.software.architectures.team31.isapharmacy.dto;

public class EmployeeComplaintCreateDTO {

	private Long patientId;
	private Long employeeId;
	private String complaintText;
	
	public EmployeeComplaintCreateDTO() {
		super();
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
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
