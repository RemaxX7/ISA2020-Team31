package internet.software.architectures.team31.isapharmacy.domain.patient;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;

@Entity
@Table(name = "appointments")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
public abstract class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected Long id;
	@Embedded
	protected DateRange dateRange;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	protected Patient patient;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	protected Pharmacy pharmacy;
	@Column(name = "price")
	protected Double price;
	@Column(name = "report")
	protected String report;
	@Column(name = "appointment_medicine_items")
	@ElementCollection(targetClass=AppointmentMedicineItem.class)
	protected List<AppointmentMedicineItem> appointmentMedicineList;
	
	public Appointment() {
		super();
	}

	public Appointment(Long id, DateRange dateRange, Patient patient, Pharmacy pharmacy,
			Double price, String report, List<AppointmentMedicineItem> appointmentMedicineList) {
		super();
		this.id = id;
		this.dateRange = dateRange;
		this.patient = patient;
		this.pharmacy = pharmacy;
		this.price = price;
		this.report = report;
		this.appointmentMedicineList = appointmentMedicineList;
	}

	public DateRange getDateRange() {
		return dateRange;
	}

	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public List<AppointmentMedicineItem> getAppointmentMedicineList() {
		return appointmentMedicineList;
	}

	public void setAppointmentMedicineList(List<AppointmentMedicineItem> appointmentMedicineList) {
		this.appointmentMedicineList = appointmentMedicineList;
	}
}
