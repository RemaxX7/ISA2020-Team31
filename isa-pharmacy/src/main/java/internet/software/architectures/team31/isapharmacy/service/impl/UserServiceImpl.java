package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.domain.users.User;
import internet.software.architectures.team31.isapharmacy.dto.EmployeeProfileEditDTO;
import internet.software.architectures.team31.isapharmacy.dto.PasswordChangeDTO;
import internet.software.architectures.team31.isapharmacy.dto.UserRegisterDTO;
import internet.software.architectures.team31.isapharmacy.dto.UserViewDTO;
import internet.software.architectures.team31.isapharmacy.exception.UsernameNotUniqueException;
import internet.software.architectures.team31.isapharmacy.repository.EmployeeRepository;
import internet.software.architectures.team31.isapharmacy.repository.PatientRepository;
import internet.software.architectures.team31.isapharmacy.repository.UserRepository;
import internet.software.architectures.team31.isapharmacy.service.AuthorityService;
import internet.software.architectures.team31.isapharmacy.service.CityService;
import internet.software.architectures.team31.isapharmacy.service.DermatologistService;
import internet.software.architectures.team31.isapharmacy.service.EmailService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private CityService cityService;
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private DermatologistService dermService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Patient registerPatient(UserRegisterDTO dto) throws UsernameNotUniqueException {
		if(findByEmail(dto.getEmail()) != null) {
			throw new UsernameNotUniqueException("Username " + dto.getEmail() + " is already registered.");
		}
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		Patient patient = new Patient(dto);
		patient.getAddress().setCity(cityService.findById(dto.getAddress().getCityId()));
		patient.setAuthorities(authorityService.findByname("ROLE_USER"));
		patient.setActivationToken(UUID.randomUUID() + "_" + dto.getUidn());
		sendActivationEmail(patient);
		return userRepository.save(patient);
	}
	
	@Override
	public void activate(String token) throws AccountException {
		Patient patient = userRepository.findByActivationToken(token);
		if(patient == null || patient.isEnabled()) {
			throw new AccountException("Account is already activated.");
		}
		
		patient.setEnabled(true);
		userRepository.save(patient);
	}
	
	private void sendActivationEmail(Patient patient) {
		emailService.sendEmail(patient.getEmail(), "Account activation", getActivationText(patient));
	}
	
	private String getActivationText(Patient patient) {
		StringBuilder text = new StringBuilder("Hello, " + patient.getName() + ".\r\n");
		text.append("Click the following link to activate your account: \r\n");
		text.append("http://localhost:8080/auth/activate/" + patient.getActivationToken());
		return text.toString();
	}

	@Override
	public String findTypeById(Long id) {
		return this.userRepository.findTypeById(id);
	}

	@Override
	public User findByUidn(String uidn) {
		User user = userRepository.findByUidn(uidn);
		return user;
	}
	
	@Override
	@Transactional
	public User findByUidnLock(String uidn) {
		User user = patientRepository.findByUidn(uidn);
		return user;
	}

	@Override
	public User employeeEditProfile(EmployeeProfileEditDTO dto) {
		User user = findByUidn(dto.getUidn());
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setEmail(dto.getEmail());
		return userRepository.save(user);
	}

	@Override
	public User employeeEditPassword(PasswordChangeDTO dto) {
		User user = findByUidn(dto.getUidn());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Collection<UserViewDTO> findAllEmployees() {
		return employeeRepository.findAll().stream().map(employee -> new UserViewDTO(employee)).collect(Collectors.toList());
	}

	
}
