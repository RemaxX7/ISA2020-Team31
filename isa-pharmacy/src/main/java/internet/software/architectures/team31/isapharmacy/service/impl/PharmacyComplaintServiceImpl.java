package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.patient.ComplaintReply;
import internet.software.architectures.team31.isapharmacy.domain.patient.PharmacyComplaint;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.SystemAdmin;
import internet.software.architectures.team31.isapharmacy.dto.ComplaintReplyCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.PharmacyComplaintCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AlreadyRepliedException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidComplaintException;
import internet.software.architectures.team31.isapharmacy.repository.PharmacyComplaintRepository;
import internet.software.architectures.team31.isapharmacy.service.AppointmentService;
import internet.software.architectures.team31.isapharmacy.service.MedicineReservationService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyComplaintService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@Service
public class PharmacyComplaintServiceImpl implements PharmacyComplaintService {

	@Autowired
	private PharmacyComplaintRepository pharmacyComplaintRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private PharmacyService pharmacyService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private MedicineReservationService medicineReservationService;

	@Override
	public PharmacyComplaint save(PharmacyComplaintCreateDTO dto) throws InvalidComplaintException {
		if(!appointmentService.hasPatientVisitedPharmacy(dto.getPatientId(), dto.getPharmacyId()) ||
				!medicineReservationService.hasPatientPurchasedMedicineFromPharmacy(dto.getPatientId(), dto.getPharmacyId())) {
			//TODO: Add check for e-prescriptions
			throw new InvalidComplaintException("You cannot make a complaint for this pharmacy.");
		}
		
		PharmacyComplaint pharmacyComplaint = new PharmacyComplaint(dto);
		pharmacyComplaint.setPatient((Patient) userService.findById(dto.getPatientId()));
		pharmacyComplaint.setPharmacy(pharmacyService.findById(dto.getPharmacyId()));
		return pharmacyComplaintRepository.save(pharmacyComplaint);
	}

	@Override
	public ComplaintReply reply(ComplaintReplyCreateDTO dto) throws AlreadyRepliedException {
		PharmacyComplaint complaint = findById(dto.getComplaintId());
		if(complaint.getReply() != null) {
			throw new AlreadyRepliedException("Complaint has already been replied to.");
		}
		
		ComplaintReply reply = new ComplaintReply(dto);
		reply.setAdmin((SystemAdmin) userService.findById(dto.getAdminId()));
		complaint.setReply(reply);
		return pharmacyComplaintRepository.save(complaint).getReply();
	}
	
	@Override
	public Collection<PharmacyComplaint> findAll() {
		return pharmacyComplaintRepository.findAll();
	}

	@Override
	public Collection<PharmacyComplaint> findAllByPatientId(Long id) {
		return pharmacyComplaintRepository.findAllByPatientId(id);
	}

	@Override
	public PharmacyComplaint findById(Long id) {
		return pharmacyComplaintRepository.findById(id).orElse(null);
	}
}
