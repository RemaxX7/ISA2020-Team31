package internet.software.architectures.team31.isapharmacy.domain.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;

@Entity
@DiscriminatorValue("Dermatologist")
public class Dermatologist extends Employee {

	private static final long serialVersionUID = 1944085040268723921L;
	
	@Column(name="pharmacies_list")
	@ElementCollection(targetClass=Pharmacy.class)
	private List<Pharmacy> pharmacies;

	public Dermatologist() {
		super();
	}

	public Dermatologist(List<Pharmacy> pharmacies) {
		super();
		this.pharmacies = pharmacies;
	}

	public List<Pharmacy> getPharmacies() {
		return pharmacies;
	}

	public void setPharmacies(List<Pharmacy> pharmacies) {
		this.pharmacies = pharmacies;
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
}
