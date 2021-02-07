package internet.software.architectures.team31.isapharmacy.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;

public interface CounselingRepository extends JpaRepository<Counseling, Long> {

	Collection<Counseling> findAllByPatientId(Long id);
	Collection<Counseling> findAllByPharmacistId(Long id);
	Collection<Counseling> findAllByAppointmentStatus(AppointmentStatus status);
	Collection<Counseling> findAllByPatientIdAndAppointmentStatus(Long patientId, AppointmentStatus status);
	Counseling findOneByPatientIdAndPharmacistIdAndAppointmentStatus(Long patientId, Long pharmacistId, AppointmentStatus status);
}
