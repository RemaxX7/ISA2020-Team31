package internet.software.architectures.team31.isapharmacy.dto;

import java.util.List;

public class AppointmentFinalizationDTO {
	private String reportt;
	private String[] medicine;
	private String uidn;
	private String medicineQuantity;
	
	public AppointmentFinalizationDTO() {
		super();
	}
	public AppointmentFinalizationDTO(String report, String[] medicine,String uidn,String medicineQuantity) {
		super();
		this.reportt = report;
		this.medicine = medicine;
		this.uidn=uidn;
		this.medicineQuantity = medicineQuantity;
	}
	public String getReport() {
		return reportt;
	}
	public void setReport(String report) {
		this.reportt = report;
	}
	public String[] getMedicine() {
		return medicine;
	}
	public void setMedicine(String[] medicine) {
		this.medicine = medicine;
	}
	public String getUidn() {
		return uidn;
	}
	public void setUidn(String uidn) {
		this.uidn = uidn;
	}
	public String getQuantity() {
		return medicineQuantity;
	}
	public void setQuantity(String medicineQuantity) {
		this.medicineQuantity = medicineQuantity;
	}
	
	
}
