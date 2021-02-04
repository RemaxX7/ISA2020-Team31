package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;

import internet.software.architectures.team31.isapharmacy.domain.patient.PharmacyComplaint;

public interface PharmacyComplaintService {

	PharmacyComplaint save(PharmacyComplaint pharmacyComplaint);
	Collection<PharmacyComplaint> findAll();
	Collection<PharmacyComplaint> findAllByPatientId(Long id);
	PharmacyComplaint findById(Long id);
}
