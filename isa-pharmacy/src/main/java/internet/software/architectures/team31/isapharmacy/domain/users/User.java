package internet.software.architectures.team31.isapharmacy.domain.users;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import internet.software.architectures.team31.isapharmacy.domain.location.Address;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
public abstract class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	private Integer id;
	
	@NotBlank(message = "Name is mandatory")
	@Column(nullable = false)
	private String name;
	
	@NotBlank(message = "Surname is mandatory")
	@Column(nullable = false)
	private String surname;
	
	@NotBlank(message = "Uidn is mandatory")
	@Column(unique=true, nullable=false)
	private String uidn;
	
	@NotBlank(message = "Username is mandatory")
	@Column(nullable = false)
	private String username;
	
	@NotBlank(message = "Password is mandatory")
	@Column(nullable = false)
	private String password;
	
	@NotBlank(message = "Email is mandatory")
	@Column(unique=true, nullable=false)
	private String email;
	
	@NotBlank(message = "Phone number is mandatory")
	@Column(name="phone_number",nullable=false)
	private String phoneNumber;
	
	@NotBlank(message = "Phone number is mandatory")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Address address;
	
	public User() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getUidn() {
		return uidn;
	}

	public void setUidn(String uidn) {
		this.uidn = uidn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
