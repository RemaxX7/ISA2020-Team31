package internet.software.architectures.team31.isapharmacy.service;

import java.util.List;

import javax.security.auth.login.AccountException;

import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.User;
import internet.software.architectures.team31.isapharmacy.dto.EmployeeProfileEditDTO;
import internet.software.architectures.team31.isapharmacy.dto.PatientProfileDTO;
import internet.software.architectures.team31.isapharmacy.dto.PatientRegisterDTO;
import internet.software.architectures.team31.isapharmacy.exception.UsernameNotUniqueException;

public interface UserService {
	
	User save(User user);
    User findById(Long id);
    User findByEmail(String email);
    List<User> findAll ();
    Patient registerPatient(PatientRegisterDTO dto) throws UsernameNotUniqueException;
    void activate(String token) throws AccountException;
    User findByUidn(String uidn);
    User employeeEditProfile(EmployeeProfileEditDTO dto);
}
