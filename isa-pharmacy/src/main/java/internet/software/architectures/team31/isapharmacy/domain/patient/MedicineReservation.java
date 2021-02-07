package internet.software.architectures.team31.isapharmacy.domain.patient;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReservationCreateDTO;

@Entity
@Table(name = "medicine_reservations")
public class MedicineReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "medicine_reservation_status")
	private MedicineReservationStatus medicineReservationStatus;
	@Column
	private String code;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Patient patient;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;
	@Column(name = "pick_up_date")
	private LocalDate pickUpDate;
	@Column(name = "medicine_reservation_items")
	@ElementCollection(targetClass = MedicineReservationItem.class)
	private List<MedicineReservationItem> medicineReservationItems;
	private Double price;
	
	public MedicineReservation() {
		super();
	}

	public MedicineReservation(Long id, MedicineReservationStatus medicineReservationStatus, String code,
			Patient patient, Pharmacy pharmacy, LocalDate pickUpDate,
			List<MedicineReservationItem> medicineReservationItems, Double price) {
		super();
		this.id = id;
		this.medicineReservationStatus = medicineReservationStatus;
		this.code = code;
		this.patient = patient;
		this.pharmacy = pharmacy;
		this.pickUpDate = pickUpDate;
		this.medicineReservationItems = medicineReservationItems;
		this.price = price;
	}
	
	public MedicineReservation(MedicineReservationCreateDTO dto) {
		super();
		this.medicineReservationStatus = MedicineReservationStatus.CREATED;
		this.pickUpDate = dto.getPickUpDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MedicineReservationStatus getMedicineReservationStatus() {
		return medicineReservationStatus;
	}

	public void setMedicineReservationStatus(MedicineReservationStatus medicineReservationStatus) {
		this.medicineReservationStatus = medicineReservationStatus;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public LocalDate getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(LocalDate pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public List<MedicineReservationItem> getMedicineReservationItems() {
		return medicineReservationItems;
	}

	public void setMedicineReservationItems(List<MedicineReservationItem> medicineReservationItems) {
		this.medicineReservationItems = medicineReservationItems;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}