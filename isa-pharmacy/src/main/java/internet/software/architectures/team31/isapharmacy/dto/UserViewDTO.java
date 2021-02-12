package internet.software.architectures.team31.isapharmacy.dto;

import internet.software.architectures.team31.isapharmacy.domain.users.User;

public class UserViewDTO {

	private Long id;
	private String name;
	private String surname;
	private String email;
	
	public UserViewDTO() {
		super();
	}

	public UserViewDTO(Long id, String name, String surname, String email) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	public UserViewDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.email = user.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
