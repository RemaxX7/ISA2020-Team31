package internet.software.architectures.team31.isapharmacy.dto;

public class AppointmentScheduleDTO {

	private Long appointmentId;
	
	public AppointmentScheduleDTO() {
		super();
	}
	
	public AppointmentScheduleDTO(Long appointmentId) {
		super();
		this.appointmentId = appointmentId;
	}
	
	public Long getAppointmentId() {
		return appointmentId;
	}
	
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
}
