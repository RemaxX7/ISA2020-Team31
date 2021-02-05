package internet.software.architectures.team31.isapharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.users.User;
import internet.software.architectures.team31.isapharmacy.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User findOne(Long id) {
		return userRepository.findById(id).orElseGet(null);
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
}
