package internet.software.architectures.team31.isapharmacy.dto;

public class AdditionalExamSchedulingDTO {
	private String uidn;
	private String date;
	private String employeeuidn;
	private String id;
	public AdditionalExamSchedulingDTO() {
		super();
	}
	public AdditionalExamSchedulingDTO(String uidn, String date, String employeeuidn,String id) {
		super();
		this.uidn = uidn;
		this.date = date;
		this.employeeuidn = employeeuidn;
		this.id = id;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
