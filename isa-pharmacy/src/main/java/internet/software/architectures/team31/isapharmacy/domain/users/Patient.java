package internet.software.architectures.team31.isapharmacy.domain.users;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import internet.software.architectures.team31.isapharmacy.domain.location.Address;
import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.dto.UserRegisterDTO;
@Entity
@DiscriminatorValue("Patient")
public class Patient extends User {

	private static final long serialVersionUID = -7226276162847290176L;
	
	@Column
	private Integer penalty;
	@Column(name="bought_medicine_list")
	@ElementCollection(targetClass=Medicine.class)
	private List<Medicine> boughtMedicineList;
	@ManyToMany
	@JoinTable(name = "patients_allergies")
	private List<Medicine> allergies;
	@Column(name = "activation_token")
	private String activationToken;
	
	public Patient() {
		super();
	}

	public Patient(Integer penalty, List<Medicine> boughtMedicineList, List<Medicine> allergies, String activationToken) {
		super();
		this.penalty = penalty;
		this.boughtMedicineList = boughtMedicineList;
		this.allergies = allergies;
		this.activationToken = activationToken;
	}

	public Patient(UserRegisterDTO dto) {
		super();
		this.name = dto.getName(); 
		this.surname = dto.getSurname();
		this.username = dto.getUsername();
		this.uidn = dto.getUidn();
		this.email = dto.getEmail();
		this.password = dto.getPassword();
		this.phoneNumber = dto.getPhoneNumber();
		this.address = new Address(dto.getAddress());
		this.penalty = 0;
	}

	public Integer getPenalty() {
		return penalty;
	}

	public void setPenalty(Integer penalty) {
		this.penalty = penalty;
	}

	public List<Medicine> getBoughtMedicineList() {
		return boughtMedicineList;
	}

	public void setBoughtMedicineList(List<Medicine> boughtMedicineList) {
		this.boughtMedicineList = boughtMedicineList;
	}

	public List<Medicine> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<Medicine> allergies) {
		this.allergies = allergies;
	}
	
	public String getActivationToken() {
		return activationToken;
	}
	
	public void setActivationToken(String activationToken) {
		this.activationToken = activationToken;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
}
