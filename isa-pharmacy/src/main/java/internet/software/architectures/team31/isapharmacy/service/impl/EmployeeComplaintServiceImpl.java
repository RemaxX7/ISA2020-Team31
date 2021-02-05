package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.patient.ComplaintReply;
import internet.software.architectures.team31.isapharmacy.domain.patient.EmployeeComplaint;
import internet.software.architectures.team31.isapharmacy.domain.users.Employee;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.SystemAdmin;
import internet.software.architectures.team31.isapharmacy.dto.ComplaintReplyCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.EmployeeComplaintCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.AlreadyRepliedException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidComplaintException;
import internet.software.architectures.team31.isapharmacy.repository.EmployeeComplaintRepository;
import internet.software.architectures.team31.isapharmacy.service.CounselingService;
import internet.software.architectures.team31.isapharmacy.service.EmployeeComplaintService;
import internet.software.architectures.team31.isapharmacy.service.ExamService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@Service
public class EmployeeComplaintServiceImpl implements EmployeeComplaintService {

	@Autowired
	private EmployeeComplaintRepository employeeComplaintRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ExamService examService;
	@Autowired
	private CounselingService counselingService;
	
	@Override
	public EmployeeComplaint save(EmployeeComplaintCreateDTO dto) throws InvalidComplaintException {
		if(examService.hasPatientVisitedDermatologist(dto.getPatientId(), dto.getEmployeeId()) ||
				counselingService.hasPatientVisitedPharmacist(dto.getPatientId(), dto.getEmployeeId())) {
			throw new InvalidComplaintException("You cannot make a complaint for this employee.");
		}
		
		EmployeeComplaint complaint = new EmployeeComplaint(dto);
		complaint.setPatient((Patient) userService.findById(dto.getPatientId()));
		complaint.setEmployee((Employee) userService.findById(dto.getEmployeeId()));
		return employeeComplaintRepository.save(complaint);
	}
	
	@Override
	public ComplaintReply reply(ComplaintReplyCreateDTO dto) throws AlreadyRepliedException {
		EmployeeComplaint complaint = findById(dto.getComplaintId());
		
		if(complaint.getReply() != null) {
			throw new AlreadyRepliedException("Complaint has already been replied to.");
		}
		
		ComplaintReply reply = new ComplaintReply(dto);
		reply.setAdmin((SystemAdmin) userService.findById(dto.getAdminId()));
		complaint.setReply(reply);
		return employeeComplaintRepository.save(complaint).getReply();
	}	

	@Override
	public Collection<EmployeeComplaint> findAll() {
		return employeeComplaintRepository.findAll();
	}
	
	@Override
	public Collection<EmployeeComplaint> findAllByPatientId(Long id) {
		return employeeComplaintRepository.findAllByPatientId(id);
	}

	@Override
	public EmployeeComplaint findById(Long id) {
		return employeeComplaintRepository.findById(id).orElse(null);
	}
}
