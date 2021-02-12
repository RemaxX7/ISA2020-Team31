package internet.software.architectures.team31.isapharmacy.dto;

import java.time.LocalDateTime;

public class FindAvailablePharmacistsDTO {

	private LocalDateTime dateTime;
	private Long pharmacyId;
	
	public FindAvailablePharmacistsDTO() {
		super();
	}

	public FindAvailablePharmacistsDTO(LocalDateTime dateTime, Long pharmacyId) {
		super();
		this.dateTime = dateTime;
		this.pharmacyId = pharmacyId;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
}
