package internet.software.architectures.team31.isapharmacy.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservation;
import internet.software.architectures.team31.isapharmacy.domain.users.User;
import internet.software.architectures.team31.isapharmacy.dto.EmployeeProfileEditDTO;
import internet.software.architectures.team31.isapharmacy.dto.PasswordChangeDTO;
import internet.software.architectures.team31.isapharmacy.dto.UserViewDTO;
import internet.software.architectures.team31.isapharmacy.service.CounselingService;
import internet.software.architectures.team31.isapharmacy.service.ExamService;
import internet.software.architectures.team31.isapharmacy.service.MedicineReservationService;
import internet.software.architectures.team31.isapharmacy.service.MedicineService;
import internet.software.architectures.team31.isapharmacy.service.UserDetailsServiceImpl;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@RestController
@RequestMapping(value = "api/search/employee")
public class EmployeeController {
	
	@Autowired
	private UserDetailsServiceImpl userService;
	@Autowired
	private UserService employedService;
	@Autowired
	private ExamService examService;
	@Autowired
	private MedicineReservationService medicineService;
	@Autowired
	private MedicineService medService;
	@Autowired
	private CounselingService counService;
	@GetMapping("/all")
	public ResponseEntity<Collection<User>> findAll() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}
	@GetMapping("/getbyid/{uidn}")
	public ResponseEntity<User> findByID(@PathVariable String uidn) {
		return new ResponseEntity<>(userService.findByUidn(uidn), HttpStatus.OK);
	}
	@GetMapping("/reservation/{id}/{uidn}")
	public ResponseEntity<Collection<MedicineReservation>> findReservationByID(@PathVariable String id,@PathVariable String uidn) {
		return new ResponseEntity<>(medicineService.findById(id,uidn), HttpStatus.OK);
	}
	@PostMapping(value = "/editprofile")
	public ResponseEntity<User> editUserProfile(@RequestBody EmployeeProfileEditDTO dto){
		return new ResponseEntity<>(employedService.employeeEditProfile(dto),HttpStatus.OK);
	}
	@PostMapping(value = "/editpassword")
	public ResponseEntity<User> editUserPassword(@RequestBody PasswordChangeDTO dto){
		return new ResponseEntity<>(employedService.employeeEditPassword(dto),HttpStatus.OK);
	}
	@GetMapping(value = "/freeterm/{patuidn}/{empuidn}")
	public ResponseEntity<Collection<String>> findFreeTermins(@PathVariable String patuidn,@PathVariable String empuidn) {
		return new ResponseEntity<>(examService.findTerminsByUidns(patuidn,empuidn), HttpStatus.OK);
	}
	@GetMapping(value = "/freetermpharm/{patuidn}/{empuidn}")
	public ResponseEntity<Collection<String>> findFreeTerminsPharm(@PathVariable String patuidn,@PathVariable String empuidn) {
		return new ResponseEntity<>(counService.findTerminsByUidnsPharm(patuidn,empuidn), HttpStatus.OK);
	}
	@GetMapping(value = "/counsforpharm/{uidn}/{days}")
	public ResponseEntity<Collection<Counseling>> findCounsForPharm(@PathVariable String uidn,@PathVariable String days) {
		return new ResponseEntity<>(counService.findCounsForPharm(uidn,days), HttpStatus.OK);
	}
	@GetMapping(value = "/examsforderm/{uidn}/{days}")
	public ResponseEntity<Collection<Exam>> findExamsForDerm(@PathVariable String uidn,@PathVariable String days) {
		return new ResponseEntity<>(counService.findExamsForDerm(uidn,days), HttpStatus.OK);
	}
	@GetMapping(value = "/medicineavailability/{name}/{id}")
	public ResponseEntity<String> findAvailableMedicineCount(@PathVariable String name,@PathVariable String id) {
		return new ResponseEntity<>(medService.findAvailableMedicineCount(name,id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/employees")
	public ResponseEntity<Collection<UserViewDTO>> findAllEmployees() {
		return new ResponseEntity<>(employedService.findAllEmployees(), HttpStatus.OK);
	}
}
