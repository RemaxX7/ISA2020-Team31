package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import internet.software.architectures.team31.isapharmacy.domain.patient.ComplaintReply;
import internet.software.architectures.team31.isapharmacy.domain.patient.EmployeeComplaint;
import internet.software.architectures.team31.isapharmacy.dto.ComplaintReplyCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.EmployeeComplaintCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AlreadyRepliedException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidComplaintException;

public interface EmployeeComplaintService {

	EmployeeComplaint save(EmployeeComplaintCreateDTO dto) throws InvalidComplaintException;
	ComplaintReply reply(ComplaintReplyCreateDTO dto) throws AlreadyRepliedException;
	Collection<EmployeeComplaint> findAll();
	Collection<EmployeeComplaint> findAllByPatientId(Long id);
	EmployeeComplaint findById(Long id);
}
