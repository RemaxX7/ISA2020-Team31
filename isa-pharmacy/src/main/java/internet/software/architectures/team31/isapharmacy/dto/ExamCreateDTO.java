package internet.software.architectures.team31.isapharmacy.dto;

import java.time.LocalDateTime;

public class ExamCreateDTO {

	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private Long pharmacyId;
	private Long dermatologistId;
	private Double price;
	
	public ExamCreateDTO() {
		super();
	}

	public ExamCreateDTO(LocalDateTime startDateTime, LocalDateTime endDateTime, Long pharmacyId, Long dermatologistId,
			Double price) {
		super();
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.pharmacyId = pharmacyId;
		this.dermatologistId = dermatologistId;
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

	public Long getDermatologistId() {
		return dermatologistId;
	}

	public void setDermatologistId(Long dermatologistId) {
		this.dermatologistId = dermatologistId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
