package internet.software.architectures.team31.isapharmacy.domain.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
@Entity
@DiscriminatorValue("Patient")
public class Patient extends User{
	
	@Column
	private Integer penalty;
	@Column(name="authentication_token",unique=true,nullable = false)
	@NotBlank(message = "authenticationToken is mandatory")
	private String authenticationToken;
	@Column(name="bought_medicine_list")
	@ElementCollection(targetClass=Medicine.class)
	private List<Medicine> boughtMedicineList;
	@Column
	@ElementCollection(targetClass=Medicine.class)
	private List<Medicine> allergies;
	public Patient(@NotEmpty(message = "penalty is mandatory") Integer penalty,
			@NotBlank(message = "authenticationToken is mandatory") String authenticationToken,
			List<Medicine> boughtMedicineList, List<Medicine> allergies) {
		super();
		this.penalty = penalty;
		this.authenticationToken = authenticationToken;
		this.boughtMedicineList = boughtMedicineList;
		this.allergies = allergies;
	}
	public Integer getPenalty() {
		return penalty;
	}
	public void setPenalty(Integer penalty) {
		this.penalty = penalty;
	}
	public String getAuthenticationToken() {
		return authenticationToken;
	}
	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
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
	
	
}
