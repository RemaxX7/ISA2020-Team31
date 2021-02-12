package internet.software.architectures.team31.isapharmacy.dto;

import java.time.LocalDateTime;

public class CounselingCreateDTO {

	private LocalDateTime startDateTime;
	private Long pharmacyId;
	private Long pharmacistId;
	
	public CounselingCreateDTO() {
		super();
	}

	public CounselingCreateDTO(LocalDateTime startDateTime, Long pharmacyId,
			Long pharmacistId) {
		super();
		this.startDateTime = startDateTime;
		this.pharmacyId = pharmacyId;
		this.pharmacistId = pharmacistId;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
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
}
