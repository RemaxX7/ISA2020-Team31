package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.patient.PharmacyComplaint;
import internet.software.architectures.team31.isapharmacy.repository.PharmacyComplaintRepository;
import internet.software.architectures.team31.isapharmacy.service.PharmacyComplaintService;

@Service
public class PharmacyComplaintServiceImpl implements PharmacyComplaintService {

	@Autowired
	private PharmacyComplaintRepository pharmacyComplaintRepository;

	@Override
	public PharmacyComplaint save(PharmacyComplaint pharmacyComplaint) {
		return pharmacyComplaintRepository.save(pharmacyComplaint);
	}

	@Override
	public Collection<PharmacyComplaint> findAll() {
		return pharmacyComplaintRepository.findAll();
	}

	@Override
	public Collection<PharmacyComplaint> findAllByPatientId(Long id) {
		return pharmacyComplaintRepository.findAllByPatientId(id);
	}

	@Override
	public PharmacyComplaint findById(Long id) {
		return pharmacyComplaintRepository.findById(id).orElse(null);
	}
}
