package internet.software.architectures.team31.isapharmacy.service;

import java.util.List;

import internet.software.architectures.team31.isapharmacy.domain.users.Authority;

public interface AuthorityService {

	List<Authority> findById(Long id);
	List<Authority> findByname(String name);
	
}
