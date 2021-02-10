package internet.software.architectures.team31.isapharmacy.dto;

public class AdditionalExamSchedulingDTO {
	private String uidn;
	private String date;
	private String employeeuidn;
	public AdditionalExamSchedulingDTO() {
		super();
	}
	public AdditionalExamSchedulingDTO(String uidn, String date, String employeeuidn) {
		super();
		this.uidn = uidn;
		this.date = date;
		this.employeeuidn = employeeuidn;
	}
	public String getUidn() {
		return uidn;
	}
	public void setUidn(String uidn) {
		this.uidn = uidn;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEmployeeuidn() {
		return employeeuidn;
	}
	public void setEmployeeuidn(String employeeuidn) {
		this.employeeuidn = employeeuidn;
	}
	
	
	
}
