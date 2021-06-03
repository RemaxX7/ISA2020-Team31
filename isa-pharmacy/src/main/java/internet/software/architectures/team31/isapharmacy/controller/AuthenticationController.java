package internet.software.architectures.team31.isapharmacy.controller;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.User;
import internet.software.architectures.team31.isapharmacy.dto.UserRegisterDTO;
import internet.software.architectures.team31.isapharmacy.dto.UserDetailsDTO;
import internet.software.architectures.team31.isapharmacy.dto.UserTokenState;
import internet.software.architectures.team31.isapharmacy.exception.UsernameNotUniqueException;
import internet.software.architectures.team31.isapharmacy.security.TokenUtils;
import internet.software.architectures.team31.isapharmacy.security.auth.JwtAuthenticationRequest;
import internet.software.architectures.team31.isapharmacy.service.UserDetailsServiceImpl;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		User user = userService.findByEmail(authenticationRequest.getUsername());
		UserDetailsDTO userDetails = new UserDetailsDTO(user);
		String jwt = tokenUtils.generateToken(user.getUsername());
		int expiresIn = tokenUtils.getExpiredIn()*100;
		System.out.println(expiresIn);
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn*10, userDetails));
	}
	
	@PostMapping("/logout")
	public ResponseEntity<Boolean> logout(HttpServletRequest request) {
		new SecurityContextLogoutHandler().logout(request, null, null);
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}
	
	@PostMapping(value = "/register/patient")
	public ResponseEntity<Patient> register(@RequestBody UserRegisterDTO dto) throws UsernameNotUniqueException {
		return new ResponseEntity<>(userService.registerPatient(dto), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/activate/{token}")
	public ResponseEntity<String> activate(@PathVariable String token) throws AccountException {
		userService.activate(token);
		return new ResponseEntity<>("Account activated.", HttpStatus.OK);
	}
	@PreAuthorize("hasRole('DERMATOLOGIST') or hasRole('PHARMACIST')")
	@PostMapping(value = "/refresh")
	public ResponseEntity<UserTokenState> refreshAuthenticationToken(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User user = userService.findByEmail(username);
		UserDetailsDTO userDetails = new UserDetailsDTO(user);
		System.out.println("USAO U REFRESH");
		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = tokenUtils.refreshToken(token);
			int expiresIn = tokenUtils.getExpiredIn();

			return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn, userDetails));
		} else {
			UserTokenState userTokenState = new UserTokenState();
			return ResponseEntity.badRequest().body(userTokenState);
		}
	}
}
