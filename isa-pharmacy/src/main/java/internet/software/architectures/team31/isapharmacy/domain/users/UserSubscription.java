package internet.software.architectures.team31.isapharmacy.domain.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_subscriptions")
public class UserSubscription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long userId;
	
	@Column(nullable = false)
	private Long pharmacyId;
	
	public UserSubscription() {
		
	}

	public UserSubscription(Long userId, Long pharmacyId) {
		super();
		this.userId = userId;
		this.pharmacyId = pharmacyId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	
}
