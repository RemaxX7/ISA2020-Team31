package internet.software.architectures.team31.isapharmacy.domain.users;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import internet.software.architectures.team31.isapharmacy.domain.location.Address;
import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.domain.patient.UserCategory;
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
	@Column
	@ElementCollection(targetClass=Medicine.class)
	private List<Medicine> allergies;
	@Column(name = "activation_token")
	private String activationToken;
	@Column
    private UserCategory userCategory;
	@Column 
	private Integer obtainedPoints;
	
	public Patient() {
		super();
	}

	public Patient(Integer penalty, List<Medicine> boughtMedicineList, List<Medicine> allergies, String activationToken, UserCategory userCategory, Integer obtainedPoints) {
		super();
		this.penalty = penalty;
		this.boughtMedicineList = boughtMedicineList;
		this.allergies = allergies;
		this.activationToken = activationToken;
		this.userCategory = userCategory;
		this.obtainedPoints = obtainedPoints;
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
		this.userCategory = UserCategory.REGULAR;
		this.obtainedPoints = 0;
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
	
	public UserCategory getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(UserCategory userCategory) {
		this.userCategory = userCategory;
	}
	
	public Integer getObtainedPoints() {
		return obtainedPoints;
	}

	public void setObtainedPoints(Integer obtainedPoints) {
		this.obtainedPoints = obtainedPoints;
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
