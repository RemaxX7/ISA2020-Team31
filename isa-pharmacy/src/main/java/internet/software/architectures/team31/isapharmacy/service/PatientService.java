package internet.software.architectures.team31.isapharmacy.service;

import java.util.List;

import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.domain.patient.UserCategory;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.dto.AllergiesDTO;
import internet.software.architectures.team31.isapharmacy.dto.PasswordUpdateDTO;
import internet.software.architectures.team31.isapharmacy.dto.PatientProfileDTO;
import internet.software.architectures.team31.isapharmacy.exception.PasswordControlException;

public interface PatientService {
	Patient findByUidn(String uidn);
	Exam penalize(String uidn,String date,String dermuidn);
	List<Patient> findAll();
	Counseling pharmacistPenalize(String uidn,String date,String pharmuidn);
	PatientProfileDTO getPatientProfile();
	AllergiesDTO getPatientAllergies();
	PatientProfileDTO updatePatientProfile(PatientProfileDTO dto);
	AllergiesDTO updatePatientAllergies(AllergiesDTO dto);
	Boolean updatePatientPassword(PasswordUpdateDTO dto) throws PasswordControlException;
	Patient changeUserCategory(Long userId, UserCategory userCategory);
}
