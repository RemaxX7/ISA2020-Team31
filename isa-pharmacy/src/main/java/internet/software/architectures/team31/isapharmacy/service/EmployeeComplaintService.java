package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import internet.software.architectures.team31.isapharmacy.domain.patient.EmployeeComplaint;

public interface EmployeeComplaintService {

	EmployeeComplaint save(EmployeeComplaint employeeComplaint);
	Collection<EmployeeComplaint> findAll();
	Collection<EmployeeComplaint> findAllByPatientId(Long id);
	EmployeeComplaint findById(Long id);
}
