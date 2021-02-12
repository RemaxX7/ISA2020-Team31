package internet.software.architectures.team31.isapharmacy.dto;

import java.time.LocalDateTime;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.AppointmentType;

public class PriceListItemAppointmentCreateDTO {
	
	private AppointmentType appointmentType;
	private LocalDateTime startDateTime;
	private Double price;
	
	public PriceListItemAppointmentCreateDTO(AppointmentType appointmentType, LocalDateTime startDateTime,
			Double price) {
		super();
		this.appointmentType = appointmentType;
		this.startDateTime = startDateTime;
		this.price = price;
	}

	public PriceListItemAppointmentCreateDTO() {
		super();
	}

	public AppointmentType getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
