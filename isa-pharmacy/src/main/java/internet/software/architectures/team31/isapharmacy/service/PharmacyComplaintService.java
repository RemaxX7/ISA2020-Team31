package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import internet.software.architectures.team31.isapharmacy.domain.patient.ComplaintReply;
import internet.software.architectures.team31.isapharmacy.domain.patient.PharmacyComplaint;
import internet.software.architectures.team31.isapharmacy.dto.ComplaintReplyCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.PharmacyComplaintCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AlreadyRepliedException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidComplaintException;

public interface PharmacyComplaintService {

	PharmacyComplaint save(PharmacyComplaintCreateDTO dto) throws InvalidComplaintException;
	ComplaintReply reply(ComplaintReplyCreateDTO dto) throws AlreadyRepliedException;
	Collection<PharmacyComplaint> findAll();
	Collection<PharmacyComplaint> findAllByPatientId(Long id);
	PharmacyComplaint findById(Long id);
}
