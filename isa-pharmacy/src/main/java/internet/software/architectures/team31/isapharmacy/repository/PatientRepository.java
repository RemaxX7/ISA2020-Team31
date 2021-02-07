package internet.software.architectures.team31.isapharmacy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.users.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	Patient findByUidn(String uidn);
	List<Patient> findAll();
}
