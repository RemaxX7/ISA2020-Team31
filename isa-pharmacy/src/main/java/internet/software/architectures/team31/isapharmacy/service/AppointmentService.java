package internet.software.architectures.team31.isapharmacy.service;

import java.util.List;

import internet.software.architectures.team31.isapharmacy.domain.patient.Appointment;

public interface AppointmentService {

	boolean hasPatientVisitedPharmacy(Long patientId, Long pharmacyId);
	List<Appointment> findAll();
}
