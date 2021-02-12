package internet.software.architectures.team31.isapharmacy.dto;

import internet.software.architectures.team31.isapharmacy.domain.users.User;

public class UserDetailsDTO {

	private String name;
	private String surname;
	private String uidn;
	private String email;
	private String role;
	
	public UserDetailsDTO() {
		super();
	}
	
	public UserDetailsDTO(String name, String surname, String uidn, String email, String role) {
		super();
		this.name = name;
		this.surname = surname;
		this.uidn = uidn;
		this.email = email;
		this.role = role;
	}

	public UserDetailsDTO(User user) {
		super();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.uidn = user.getUidn();
		this.email = user.getEmail();
		this.role = user.getAuthorities().iterator().next().getAuthority();
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
	
	public String getUidn() {
		return uidn;
	}

	public void setUidn(String uidn) {
		this.uidn = uidn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
