package internet.software.architectures.team31.isapharmacy.domain.pharmacy;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;

@Entity
@DiscriminatorValue("Appointment")
public class PriceListItemAppointment extends PricelistItem {	
	@Column(name="appointment_type")
	private AppointmentType appointmentType;

	public PriceListItemAppointment(Long id, DateRange interval, Double price, PriceListItemStatus status,
			AppointmentType appointmentType) {
		super(id, interval, price, status);
		this.appointmentType = appointmentType;
	}

	public PriceListItemAppointment(Long id, DateRange interval, Double price, PriceListItemStatus status) {
		super(id, interval, price, status);
	}
	

	public PriceListItemAppointment() {
		super();
	}

	public AppointmentType getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}
	
	

}
