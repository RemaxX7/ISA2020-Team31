package internet.software.architectures.team31.isapharmacy.domain.users;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;

@Entity
@DiscriminatorValue("Pharmacist")
public class Pharmacist extends Employee {

	private static final long serialVersionUID = -5538698322280650352L;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;

	public Pharmacist() {
		super();
	}

	public Pharmacist(Pharmacy pharmacy) {
		super();
		this.pharmacy = pharmacy;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
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
