package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.patient.PharmacyComplaint;

public interface PharmacyComplaintRepository extends JpaRepository<PharmacyComplaint, Long> {

	Collection<PharmacyComplaint> findAllByPatientId(Long id);
}
