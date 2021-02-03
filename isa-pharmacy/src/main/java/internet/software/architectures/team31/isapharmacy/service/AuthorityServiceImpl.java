package internet.software.architectures.team31.isapharmacy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.users.Authority;
import internet.software.architectures.team31.isapharmacy.repository.AuthorityRepository;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	  private AuthorityRepository authorityRepository;
	
	@Override
	public List<Authority> findById(Long id) {
		Authority authority = this.authorityRepository.getOne(id);
	    List<Authority> authorities = new ArrayList<>();
	    authorities.add(authority);
	    return authorities;
	}

	@Override
	public List<Authority> findByname(String name) {
		Authority authority = this.authorityRepository.findByName(name);
	    List<Authority> authorities = new ArrayList<>();
	    authorities.add(authority);
	    return authorities;
	}

}
