package internet.software.architectures.team31.isapharmacy.service;

import java.util.List;

import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.domain.patient.UserCategory;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;

public interface PatientService {
	Patient findByUidn(String uidn);
	Exam penalize(String uidn);
	List<Patient> findAll();
	Counseling pharmacistPenalize(String uidn);
	Patient changeUserCategory(Long userId, UserCategory userCategory);
}
