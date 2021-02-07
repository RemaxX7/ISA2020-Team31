package internet.software.architectures.team31.isapharmacy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.users.User;
import internet.software.architectures.team31.isapharmacy.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with email '%s'.", username));
		} else {
			return user;
		}
	}
	public List<User> getAllUsers() {
		ArrayList<User> userList =  (ArrayList<User>) userRepository.findAll();
		
		return userList;
	}
	public User findByUidn(String uidn) {
		User user = userRepository.findByUidn(uidn);
		return user;
	}
}
