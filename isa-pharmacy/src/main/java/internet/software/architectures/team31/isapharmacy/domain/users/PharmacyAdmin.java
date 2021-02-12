package internet.software.architectures.team31.isapharmacy.domain.users;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;

@Entity
@DiscriminatorValue("PharmacyAdmin")
public class PharmacyAdmin extends User {

	private static final long serialVersionUID = -4500329736789929L;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;
	
	
	
	public PharmacyAdmin() {
		super();
	}

	public PharmacyAdmin(Pharmacy pharmacy) {
		super();
		this.pharmacy = pharmacy;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

}
