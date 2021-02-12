package internet.software.architectures.team31.isapharmacy.service.impl;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservation;
import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservationStatus;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReservationCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReservationViewDTO;
import internet.software.architectures.team31.isapharmacy.exception.CancelMedicineReservationException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;
import internet.software.architectures.team31.isapharmacy.repository.MedicineReservationRepository;
import internet.software.architectures.team31.isapharmacy.service.EmailService;
import internet.software.architectures.team31.isapharmacy.service.MedicineReservationService;
import internet.software.architectures.team31.isapharmacy.service.MedicineService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@Service
public class MedicineReservationServiceImpl implements MedicineReservationService {

	@Autowired
	private MedicineReservationRepository medicineReservationRepository;
	@Autowired
	private PharmacyService pharmacyService;
	@Autowired
	private UserService userService;
	@Autowired
	private MedicineService medicineService;
	@Autowired
	private EmailService emailService;

	@Override
	public MedicineReservation save(MedicineReservationCreateDTO dto) throws PenaltyException {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if(patient.getPenalty() >= 3) {
			throw new PenaltyException("Cannot make medicine reservations. Maximum number of penalties exceeded.");
		}
		
		//TODO: Check inventory before creating the reservation
		
		MedicineReservation reservation = new MedicineReservation(dto);
		reservation.setCode(UUID.randomUUID().toString().substring(0, 13));
		reservation.setPharmacy(pharmacyService.findById(dto.getPharmacyId()));
		reservation.setPatient(patient);
		reservation.setMedicine(medicineService.findById(dto.getMedicineId()));
		sendReservationEmail(reservation);
		return medicineReservationRepository.save(reservation);
	}

	@Override
	public MedicineReservation cancel(Long id) throws CancelMedicineReservationException {
		MedicineReservation reservation = findById(id);
		
		LocalDate currentDate = LocalDate.now();
		if(!currentDate.plusDays(1).isBefore(reservation.getPickUpDate())) {
			throw new CancelMedicineReservationException("Medicine reservation cannot be cancelled 24 hours before pick-up date.");
		}
		reservation.setMedicineReservationStatus(MedicineReservationStatus.CANCELLED);
		return medicineReservationRepository.save(reservation);
	}

	@Override
	public Collection<MedicineReservation> findAll() {
		return medicineReservationRepository.findAll();
	}

	@Override
	public Page<MedicineReservationViewDTO> findAllByPatient(Pageable pageable) {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return medicineReservationRepository.findAllByPatientId(patient.getId(), pageable)
				.map(reservation -> new MedicineReservationViewDTO(reservation));
	}
	
	@Override
	public Page<MedicineReservationViewDTO> findAllByPatientAndMedicineReservationStatus(MedicineReservationStatus status, Pageable pageable) {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return medicineReservationRepository.findAllByPatientIdAndMedicineReservationStatus(patient.getId(), status, pageable)
				.map(reservation -> new MedicineReservationViewDTO(reservation));
	}

	@Override
	public MedicineReservation findById(Long id) {
		return medicineReservationRepository.findById(id).orElse(null);
	}

	@Override
	public boolean hasPatientPurchasedMedicineFromPharmacy(Long patientId, Long pharmacyId) {
		return medicineReservationRepository.existsByPatientIdAndPharmacyIdAndMedicineReservationStatus(patientId, pharmacyId, MedicineReservationStatus.FINISHED);
	}

	@Override
	public boolean hasPatientPurchasedMedicine(Long patientId, Long medicineId) {
		Collection<MedicineReservation> reservations = medicineReservationRepository.findAllByPatientIdAndMedicineReservationStatus(patientId, MedicineReservationStatus.FINISHED);
		for(MedicineReservation reservation : reservations) {
			if(reservation.getId() == medicineId) {
				return true;
			}
		}
		return false;
	}
	
	private void sendReservationEmail(MedicineReservation reservation) {
		emailService.sendEmail(reservation.getPatient().getEmail(), "Medicine reservation confirmation", getReservationEmailText(reservation));
	}
	
	private String getReservationEmailText(MedicineReservation reservation) {
		StringBuilder text = new StringBuilder("Hello, " + reservation.getPatient().getName() + ". Your medicine reservation pick-up code is: " + reservation.getCode() + "\r\n");
		text.append("Pharmacy: " + reservation.getPharmacy().getName() + "\r\n");
		text.append("Address: " + reservation.getPharmacy().getAddress() + "\r\n");
		text.append("Pick-up date: " + reservation.getPickUpDate() + "\r\n");
		text.append("Medicine: " + reservation.getMedicine().getName());
		
		return text.toString();
	}
}
