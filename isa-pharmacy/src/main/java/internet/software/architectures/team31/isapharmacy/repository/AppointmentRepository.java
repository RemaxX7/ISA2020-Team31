package internet.software.architectures.team31.isapharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.patient.Appointment;
import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

	boolean existsByPatientIdAndPharmacyIdAndAppointmentStatus(Long patientId, Long pharmacyId, AppointmentStatus appointmentStatus);
}
