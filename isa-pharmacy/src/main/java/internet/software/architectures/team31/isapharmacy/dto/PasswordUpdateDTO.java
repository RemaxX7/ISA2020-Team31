package internet.software.architectures.team31.isapharmacy.dto;

public class PasswordUpdateDTO {

	private String password;
	private String passwordControl;
	
	public PasswordUpdateDTO() {
		super();
	}

	public PasswordUpdateDTO(String password, String passwordControl) {
		super();
		this.password = password;
		this.passwordControl = passwordControl;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordControl() {
		return passwordControl;
	}

	public void setPasswordControl(String passwordControl) {
		this.passwordControl = passwordControl;
	}
}
