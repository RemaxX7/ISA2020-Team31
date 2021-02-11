package internet.software.architectures.team31.isapharmacy.dto;

public class EmployeeProfileEditDTO {
	private String name;
	private String surname;
	private String email;
	private String uidn;
	
	public EmployeeProfileEditDTO() {
		super();
	}

	public EmployeeProfileEditDTO(String name, String surname, String email,String uidn) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.uidn = uidn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUidn() {
		return uidn;
	}

	public void setUidn(String uidn) {
		this.uidn = uidn;
	}
	
}
