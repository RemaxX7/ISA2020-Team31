package internet.software.architectures.team31.isapharmacy.service;

import java.util.List;

import internet.software.architectures.team31.isapharmacy.domain.users.User;

public interface UserService {
	
    User findById(Long id);
    User findByEmail(String email);
    List<User> findAll ();
    
}
