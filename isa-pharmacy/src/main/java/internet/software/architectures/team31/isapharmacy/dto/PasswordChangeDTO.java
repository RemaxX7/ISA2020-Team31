package internet.software.architectures.team31.isapharmacy.dto;

public class PasswordChangeDTO {
	private String password;
	private String uidn;
	
	public PasswordChangeDTO() {
		super();
	}

	public PasswordChangeDTO(String password, String uidn) {
		super();
		this.password = password;
		this.uidn = uidn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUidn() {
		return uidn;
	}

	public void setUidn(String uidn) {
		this.uidn = uidn;
	}
	
}
