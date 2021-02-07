package internet.software.architectures.team31.isapharmacy.service;

public interface AppointmentService {

	boolean hasPatientVisitedPharmacy(Long patientId, Long pharmacyId);
}
