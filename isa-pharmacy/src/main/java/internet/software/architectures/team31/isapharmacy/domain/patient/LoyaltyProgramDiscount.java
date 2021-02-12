package internet.software.architectures.team31.isapharmacy.domain.patient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class LoyaltyProgramDiscount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Medicine shape is mandatory")
	@Column(nullable = false)
	LoyaltyProgramCategory loyaltyProgramCategory;
	
	@NotNull(message = "Percentage for loyalty program category is mandatory")
	@Column(nullable = false)
	private Double percentage;
	
	public LoyaltyProgramDiscount() {
		
	}

	public LoyaltyProgramDiscount(Long id,
			@NotNull(message = "Medicine shape is mandatory") LoyaltyProgramCategory loyaltyProgramCategory,
			@NotNull(message = "Percentage for loyalty program category is mandatory") Double percentage) {
		super();
		this.id = id;
		this.loyaltyProgramCategory = loyaltyProgramCategory;
		this.percentage = percentage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoyaltyProgramCategory getLoyaltyProgramCategory() {
		return loyaltyProgramCategory;
	}

	public void setLoyaltyProgramCategory(LoyaltyProgramCategory loyaltyProgramCategory) {
		this.loyaltyProgramCategory = loyaltyProgramCategory;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
}
