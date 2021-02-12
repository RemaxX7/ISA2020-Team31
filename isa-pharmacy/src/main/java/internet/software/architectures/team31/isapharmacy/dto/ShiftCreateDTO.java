package internet.software.architectures.team31.isapharmacy.dto;

import java.time.LocalDateTime;

public class ShiftCreateDTO {
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private Long pharmacyId;
	private Long employeeId;
	
	public ShiftCreateDTO(LocalDateTime startDateTime, LocalDateTime endDateTime, Long pharmacyId, Long employeeId) {
		super();
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.pharmacyId = pharmacyId;
		this.employeeId = employeeId;
	}

	public ShiftCreateDTO() {
		super();
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

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	
	
}
