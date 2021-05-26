package internet.software.architectures.team31.isapharmacy.service;

import java.util.Collection;
import java.util.List;

import javax.security.auth.login.AccountException;

import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.User;
import internet.software.architectures.team31.isapharmacy.dto.EmployeeProfileEditDTO;
import internet.software.architectures.team31.isapharmacy.dto.PasswordChangeDTO;
import internet.software.architectures.team31.isapharmacy.dto.UserRegisterDTO;
import internet.software.architectures.team31.isapharmacy.dto.UserViewDTO;
import internet.software.architectures.team31.isapharmacy.exception.UsernameNotUniqueException;

public interface UserService {
	
	User save(User user);
    User findById(Long id);
    User findByEmail(String email);
    String findTypeById(Long id);
    List<User> findAll ();
    Patient registerPatient(UserRegisterDTO dto) throws UsernameNotUniqueException;
    void activate(String token) throws AccountException;
    User findByUidn(String uidn);
    User findByUidnLock(String uidn);
    User employeeEditProfile(EmployeeProfileEditDTO dto);
    User employeeEditPassword(PasswordChangeDTO dto);
    Collection<UserViewDTO> findAllEmployees();
    
}
