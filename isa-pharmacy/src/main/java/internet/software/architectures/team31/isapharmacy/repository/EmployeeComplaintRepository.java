package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.patient.EmployeeComplaint;

public interface EmployeeComplaintRepository extends JpaRepository<EmployeeComplaint, Long> {

	Collection<EmployeeComplaint> findAllByPatientId(Long id);
}
