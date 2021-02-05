package internet.software.architectures.team31.isapharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.action.Action;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.service.ActionService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ActionService actionService;

	@GetMapping("/action/{userId}/{actionId}")
	//@PreAuthorize("hasRole('USER')")
	public Action subscribeToAction(@PathVariable Long userId, @PathVariable Long actionId) {
		Patient patient = (Patient) userService.findOne(userId);
		Action action = actionService.findOne(actionId);
		
		patient.getActions().add(action);
		userService.save(patient);
		return action;		
	}
}
