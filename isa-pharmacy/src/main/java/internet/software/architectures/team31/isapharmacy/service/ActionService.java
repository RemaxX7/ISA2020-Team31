package internet.software.architectures.team31.isapharmacy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.action.Action;
import internet.software.architectures.team31.isapharmacy.repository.ActionRepository;

@Service
public class ActionService {

	@Autowired
	private ActionRepository actionRepository;
	
	public Action findOne(Long id) {
		return actionRepository.findById(id).orElseGet(null);
	}

	public Action save(Action action) {
		return actionRepository.save(action);
	}
}
