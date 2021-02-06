package internet.software.architectures.team31.isapharmacy.dto;

import java.time.LocalDateTime;

public class CounselingCreateDTO {

	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private Long pharmacyId;
	private Long pharmacistId;
	private Double price;
	
	public CounselingCreateDTO() {
		super();
	}

	public CounselingCreateDTO(LocalDateTime startDateTime, LocalDateTime endDateTime, Long pharmacyId,
			Long pharmacistId, Double price) {
		super();
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.pharmacyId = pharmacyId;
		this.pharmacistId = pharmacistId;
		this.price = price;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public Long getPharmacistId() {
		return pharmacistId;
	}

	public void setPharmacistId(Long pharmacistId) {
		this.pharmacistId = pharmacistId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
