package internet.software.architectures.team31.isapharmacy.domain.action;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "actions")
public class Action {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Action name is mandatory")
	@Column(nullable = false)
	private String name;
	
	@NotBlank(message = "Action description is mandatory")
	@Column(nullable = false)
	private String description;
	
	@NotBlank(message = "Action start date is mandatory")
	@Column(nullable = false)
	private Timestamp startDate;
	
	@NotBlank(message = "Action end date is mandatory")
	@Column(nullable = false)
	private Timestamp endDate;
	
	@NotBlank(message = "Pharmacy identifier is mandatory")
	@Column(nullable = false)
	private Long pharmacyId;
	
	public Action() {
		
	}

	public Action(Long id, @NotBlank(message = "Action name is mandatory") String name,
			@NotBlank(message = "Action description is mandatory") String description,
			@NotBlank(message = "Action start date is mandatory") Timestamp startDate,
			@NotBlank(message = "Action end date is mandatory") Timestamp endDate,
			@NotBlank(message = "Pharmacy identifier is mandatory") Long pharmacyId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.pharmacyId = pharmacyId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	
}
