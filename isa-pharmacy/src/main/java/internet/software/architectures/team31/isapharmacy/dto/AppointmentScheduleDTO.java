package internet.software.architectures.team31.isapharmacy.dto;

public class AppointmentScheduleDTO {

	private Long patientId;
	private Long appointmentId;
	
	public AppointmentScheduleDTO() {
		super();
	}
	
	public AppointmentScheduleDTO(Long patientId, Long appointmentId) {
		super();
		this.patientId = patientId;
		this.appointmentId = appointmentId;
	}
	
	public Long getPatientId() {
		return patientId;
	}
	
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	
	public Long getAppointmentId() {
		return appointmentId;
	}
	
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
}
