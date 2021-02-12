package internet.software.architectures.team31.isapharmacy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.repository.AppointmentRepository;
import internet.software.architectures.team31.isapharmacy.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public boolean hasPatientVisitedPharmacy(Long patientId, Long pharmacyId) {
		return appointmentRepository.existsByPatientIdAndPharmacyIdAndAppointmentStatus(patientId, pharmacyId, AppointmentStatus.FINISHED);
	}
}
