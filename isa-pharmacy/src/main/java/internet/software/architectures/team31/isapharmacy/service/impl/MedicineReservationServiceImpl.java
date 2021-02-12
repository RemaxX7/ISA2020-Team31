package internet.software.architectures.team31.isapharmacy.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservation;
import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservationItem;
import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservationStatus;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.Pharmacist;
import internet.software.architectures.team31.isapharmacy.domain.users.User;
import internet.software.architectures.team31.isapharmacy.dto.MedicineReservationCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.CancelMedicineReservationException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;
import internet.software.architectures.team31.isapharmacy.repository.MedicineReservationRepository;
import internet.software.architectures.team31.isapharmacy.service.EmailService;
import internet.software.architectures.team31.isapharmacy.service.MedicineReservationService;
import internet.software.architectures.team31.isapharmacy.service.MedicineService;
import internet.software.architectures.team31.isapharmacy.service.PharmacistService;
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
	private PharmacistService pharmacistService;
	@Autowired
	private EmailService emailService;

	@Override
	public MedicineReservation save(MedicineReservationCreateDTO dto) throws PenaltyException {
		Patient patient = (Patient) userService.findById(dto.getPatientId());
		if(patient.getPenalty() >= 3) {
			throw new PenaltyException("Cannot make medicine reservations. Maximum number of penalties exceeded.");
		}
		
		MedicineReservation reservation = new MedicineReservation(dto);
		reservation.setCode(String.valueOf(LocalDateTime.now().hashCode()) + "_" + reservation.getId());
		reservation.setPharmacy(pharmacyService.findById(dto.getPharmacyId()));
		reservation.setPatient(patient);
		reservation.setMedicineReservationItems(dto.getMedicineReservationItems().stream()
			    .map(item -> new MedicineReservationItem(medicineService.findById(item.getMedicineId()), item.getQuantity()))
			    .collect(Collectors.toList()));
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
	public Collection<MedicineReservation> findAllByPatientId(Long id) {
		return medicineReservationRepository.findAllByPatientId(id);
	}
	
	@Override
	public Collection<MedicineReservation> findAllByPatientIdAndMedicineReservationStatus(Long patientId,
			MedicineReservationStatus status) {
		return medicineReservationRepository.findAllByPatientIdAndMedicineReservationStatus(patientId, status);
	}

	@Override
	public MedicineReservation findById(Long id) {
		return medicineReservationRepository.findById(id).orElse(null);
	}
	public MedicineReservation findByCode(String code) {
		return medicineReservationRepository.findOneByCode(code);
	}
	@Override
	public boolean hasPatientPurchasedMedicineFromPharmacy(Long patientId, Long pharmacyId) {
		return medicineReservationRepository.findOneByPatientIdAndPharmacyIdAndMedicineReservationStatus(patientId, pharmacyId, MedicineReservationStatus.FINISHED) != null;
	}

	@Override
	public boolean hasPatientPurchasedMedicine(Long patientId, Long medicineId) {
		Collection<MedicineReservation> reservations = medicineReservationRepository.findAllByPatientIdAndMedicineReservationStatus(patientId, MedicineReservationStatus.FINISHED);
		for(MedicineReservation reservation : reservations) {
			for(MedicineReservationItem item: reservation.getMedicineReservationItems()) {
				if(item.getMedicine().getId() == medicineId) {
					return true;
				}
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
		text.append("Address: " + reservation.getPharmacy().getAddress());
		text.append("Pick-up date: " + reservation.getPickUpDate() + "\r\n");
		text.append("Medicine reservation: \r\n");
		
		for(MedicineReservationItem item: reservation.getMedicineReservationItems()) {
			text.append(item.getQuantity() + " x " + item.getMedicine().getName() + "\r\n");
		}
		
		return text.toString();
	}

	@Override
	public Collection<MedicineReservation> findById(String id,String uidn) {
		ArrayList<MedicineReservation> reservList = (ArrayList<MedicineReservation>) medicineReservationRepository.findAll();
		Pharmacist user = (Pharmacist) userService.findByUidn(uidn);
		ArrayList<MedicineReservation> frontList = new ArrayList<MedicineReservation>();
		for (MedicineReservation medicineReservation : reservList) {
			if(medicineReservation.getCode().equals(id) && medicineReservation.getMedicineReservationStatus()==MedicineReservationStatus.CREATED && medicineReservation.getPharmacy().getId().equals(user.getPharmacy().getId())) {
				frontList.add(medicineReservation);
			}
		}
		return frontList;
	}

	@Override
	public MedicineReservation closeRequest(String code) throws CancelMedicineReservationException {
		MedicineReservation reservation = findByCode(code);
		
		LocalDate currentDate = LocalDate.now();
		if(!currentDate.plusDays(1).isBefore(reservation.getPickUpDate())) {
			throw new CancelMedicineReservationException("Medicine reservation cannot be cancelled 24 hours before pick-up date.");
		}
		reservation.setMedicineReservationStatus(MedicineReservationStatus.FINISHED);
		emailService.sendEmail(reservation.getPatient().getEmail(), "Medicine was picked up", "Thank you for your patronage.");
		return medicineReservationRepository.save(reservation);
	}
}
