package internet.software.architectures.team31.isapharmacy.dto;

import java.util.List;

public class AppointmentFinalizationDTO {
	private String report;
	private List<String> medicine;
	private String uidn;
	
	public AppointmentFinalizationDTO() {
		super();
	}
	public AppointmentFinalizationDTO(String report, List<String> medicine,String uidn) {
		super();
		this.report = report;
		this.medicine = medicine;
		this.uidn=uidn;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public List<String> getMedicine() {
		return medicine;
	}
	public void setMedicine(List<String> medicine) {
		this.medicine = medicine;
	}
	public String getUidn() {
		return uidn;
	}
	public void setUidn(String uidn) {
		this.uidn = uidn;
	}
	
	
}
