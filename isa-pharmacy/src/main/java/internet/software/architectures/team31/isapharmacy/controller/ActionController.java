package internet.software.architectures.team31.isapharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.action.Action;
import internet.software.architectures.team31.isapharmacy.service.ActionService;

@RestController
@RequestMapping(value = "/api/action", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActionController {

	@Autowired
	private ActionService actionService;
	
	@GetMapping(value = "/add")
	public ResponseEntity<Action> save(@RequestBody Action action) {
		return new ResponseEntity<>(actionService.save(action), HttpStatus.CREATED);
	}
}
