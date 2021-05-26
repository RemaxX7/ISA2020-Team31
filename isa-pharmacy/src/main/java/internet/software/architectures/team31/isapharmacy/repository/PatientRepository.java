package internet.software.architectures.team31.isapharmacy.repository;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import internet.software.architectures.team31.isapharmacy.domain.users.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")})
	@Transactional
	Patient findByUidn(String uidn);
	
	List<Patient> findAll();
}
