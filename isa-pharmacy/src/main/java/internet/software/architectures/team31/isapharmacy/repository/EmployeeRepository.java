package internet.software.architectures.team31.isapharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.users.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
