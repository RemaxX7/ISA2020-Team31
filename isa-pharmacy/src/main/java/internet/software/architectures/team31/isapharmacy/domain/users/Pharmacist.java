package internet.software.architectures.team31.isapharmacy.domain.users;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import internet.software.architectures.team31.isapharmacy.domain.location.Address;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.dto.UserRegisterDTO;

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

	public Pharmacist(UserRegisterDTO dto,Pharmacy pharmacy) {
		this.name = dto.getName(); 
		this.surname = dto.getSurname();
		this.username = dto.getUsername();
		this.uidn = dto.getUidn();
		this.email = dto.getEmail();
		this.password = dto.getPassword();
		this.phoneNumber = dto.getPhoneNumber();
		this.address = new Address(dto.getAddress());
		this.pharmacy=pharmacy;
		
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
