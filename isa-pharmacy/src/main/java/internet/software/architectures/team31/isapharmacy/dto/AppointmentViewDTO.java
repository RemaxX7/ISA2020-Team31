package internet.software.architectures.team31.isapharmacy.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;

public class AppointmentViewDTO {

	private Long id;
	private LocalDateTime startDateTime;
	private Long duration;
	private String pharmacyName;
	private Double price;
	private String report;
	private List<String> medicines;
	private String employeeName;
	private String employeeSurname;
	private String employeeEmail;
	private Long employeeId;
	
	public AppointmentViewDTO() {
		super();
	}
	
	public AppointmentViewDTO(Long id, LocalDateTime startDateTime, Long duration, String pharmacyName, Double price,
			String report, List<String> medicines, String employeeName, String employeeSurname, String employeeEmail, Long employeeId) {
		super();
		this.id = id;
		this.startDateTime = startDateTime;
		this.duration = duration;
		this.pharmacyName = pharmacyName;
		this.price = price;
		this.report = report;
		this.medicines = medicines;
		this.employeeName = employeeName;
		this.employeeSurname = employeeSurname;
		this.employeeEmail = employeeEmail;
		this.employeeId = employeeId;
	}
	
	public AppointmentViewDTO(Exam exam) {
		super();
		this.id = exam.getId();
		this.startDateTime = exam.getDateRange().getStartDateTime();
		this.duration = DateRange.getMinutesBetween(exam.getDateRange());
		this.pharmacyName = exam.getPharmacy().getName();
		this.price = exam.getPrice();
		this.report = exam.getReport();
		this.medicines = exam.getAppointmentMedicineItems().stream().map(item -> new String(item.getMedicine().getName())).collect(Collectors.toList());
		this.employeeName = exam.getDermatologist().getName();
		this.employeeSurname = exam.getDermatologist().getSurname();
		this.employeeEmail = exam.getDermatologist().getEmail();
		this.employeeId = exam.getDermatologist().getId();
	}
	
	public AppointmentViewDTO(Counseling counseling) {
		super();
		this.id = counseling.getId();
		this.startDateTime = counseling.getDateRange().getStartDateTime();
		this.duration = DateRange.getMinutesBetween(counseling.getDateRange());
		this.pharmacyName = counseling.getPharmacy().getName();
		this.price = counseling.getPrice();
		this.report = counseling.getReport();
		this.medicines = counseling.getAppointmentMedicineItems().stream().map(item -> new String(item.getMedicine().getName())).collect(Collectors.toList());
		this.employeeName = counseling.getPharmacist().getName();
		this.employeeSurname = counseling.getPharmacist().getSurname();
		this.employeeEmail = counseling.getPharmacist().getEmail();
		this.employeeId = counseling.getPharmacist().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public List<String> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<String> medicines) {
		this.medicines = medicines;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeSurname() {
		return employeeSurname;
	}

	public void setEmployeeSurname(String employeeSurname) {
		this.employeeSurname = employeeSurname;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
}
