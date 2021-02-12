package internet.software.architectures.team31.isapharmacy.dto;

import java.time.LocalDate;

import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservation;

public class MedicineReservationViewDTO {

	private Long id;
	private String pharmacy;
	private AddressViewDTO address;
	private LocalDate pickUpDate;
	private MedicineViewDTO medicine;
	
	public MedicineReservationViewDTO() {
		super();
	}

	public MedicineReservationViewDTO(Long id, AddressViewDTO address, LocalDate pickUpDate, MedicineViewDTO medicine, String pharmacy) {
		super();
		this.id = id;
		this.pharmacy = pharmacy; 
		this.address = address;
		this.pickUpDate = pickUpDate;
		this.medicine = medicine;
	}
	
	public MedicineReservationViewDTO(MedicineReservation reservation) {
		super();
		this.id = reservation.getId();
		this.pharmacy = reservation.getPharmacy().getName();
		this.address = new AddressViewDTO(reservation.getPharmacy().getAddress());
		this.pickUpDate = reservation.getPickUpDate();
		this.medicine = new MedicineViewDTO(reservation.getMedicine());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AddressViewDTO getAddress() {
		return address;
	}

	public void setAddress(AddressViewDTO address) {
		this.address = address;
	}

	public LocalDate getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(LocalDate pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public MedicineViewDTO getMedicine() {
		return medicine;
	}

	public void setMedicine(MedicineViewDTO medicine) {
		this.medicine = medicine;
	}

	public String getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(String pharmacy) {
		this.pharmacy = pharmacy;
	}
}
