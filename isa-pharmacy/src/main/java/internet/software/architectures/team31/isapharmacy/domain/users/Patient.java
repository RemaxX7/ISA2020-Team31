package internet.software.architectures.team31.isapharmacy.domain.users;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import internet.software.architectures.team31.isapharmacy.domain.action.Action;
import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;

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
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_actions",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "action_id", referencedColumnName = "id"))
	private Set<Action> actions;

	public Integer getPenalty() {
		return penalty;
	}

	public Set<Action> getActions() {
		return actions;
	}

	public void setActions(Set<Action> actions) {
		this.actions = actions;
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
