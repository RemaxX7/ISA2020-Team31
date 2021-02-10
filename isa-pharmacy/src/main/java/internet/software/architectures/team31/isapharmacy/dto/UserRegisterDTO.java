package internet.software.architectures.team31.isapharmacy.dto;

public class UserRegisterDTO {

	private String name;
	private String surname;
	private String username;
	private String uidn;
	private String email;
	private String password;
	private String phoneNumber;
	private AddressCreateDTO address;
	
	public UserRegisterDTO() {
		super();
	}

	public UserRegisterDTO(String name, String surname, String username, String uidn, String email, String password,
			String phoneNumber, AddressCreateDTO address) {
		super();
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.uidn = uidn;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public AddressCreateDTO getAddress() {
		return address;
	}

	public void setAddress(AddressCreateDTO address) {
		this.address = address;
	}
}
