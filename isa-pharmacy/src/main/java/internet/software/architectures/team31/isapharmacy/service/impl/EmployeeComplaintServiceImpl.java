package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.patient.EmployeeComplaint;
import internet.software.architectures.team31.isapharmacy.repository.EmployeeComplaintRepository;
import internet.software.architectures.team31.isapharmacy.service.EmployeeComplaintService;

@Service
public class EmployeeComplaintServiceImpl implements EmployeeComplaintService {

	@Autowired
	private EmployeeComplaintRepository employeeComplaintRepository;
	
	@Override
	public EmployeeComplaint save(EmployeeComplaint employeeComplaint) {
		return employeeComplaintRepository.save(employeeComplaint);
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
