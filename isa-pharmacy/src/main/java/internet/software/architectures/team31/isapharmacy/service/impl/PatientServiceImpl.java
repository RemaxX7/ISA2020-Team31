package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;
import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.domain.patient.UserCategory;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.dto.AllergiesDTO;
import internet.software.architectures.team31.isapharmacy.dto.MedicineViewDTO;
import internet.software.architectures.team31.isapharmacy.dto.PasswordUpdateDTO;
import internet.software.architectures.team31.isapharmacy.dto.PatientProfileDTO;
import internet.software.architectures.team31.isapharmacy.exception.PasswordControlException;
import internet.software.architectures.team31.isapharmacy.repository.CounselingRepository;
import internet.software.architectures.team31.isapharmacy.repository.ExamRepository;
import internet.software.architectures.team31.isapharmacy.repository.PatientRepository;
import internet.software.architectures.team31.isapharmacy.service.CityService;
import internet.software.architectures.team31.isapharmacy.service.MedicineService;
import internet.software.architectures.team31.isapharmacy.service.PatientService;
import internet.software.architectures.team31.isapharmacy.service.UserService;
@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private ExamServiceImpl examService;
	@Autowired
	private CounselingServiceImpl counsService;
	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private CounselingRepository counsRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private CityService cityService;
	@Autowired
	private MedicineService medicineService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Patient findByUidn(String uidn) {
		return patientRepository.findByUidn(uidn);
	}
	@Override
	public Exam penalize(String uidn,String date,String dermuidn){
		List<Exam> exam = (List<Exam>) examService.findAll();
		String newDate = "";
		String mon = "0";
		String day = "0";
		if(date.endsWith(":0")) {
			newDate = date.concat("0");
		}
		String[] lastDate = newDate.split("T");
		String[] splits = lastDate[0].split("-");
		if(splits[1].length()==1) {
			splits[1]=mon.concat(splits[1]);;
		}
		if(splits[2].length()==1) {
			splits[2]=day.concat(splits[2]);
		}
		String fullDate= splits[0]+"-"+splits[1]+"-"+splits[2]+"T"+lastDate[1];
		for (Exam ex : exam) {
			System.out.println(ex.getDateRange().getStartDateTime());
			System.out.println(ex.getPatient().getUidn());
			System.out.println(ex.getDermatologist().getUidn());
			if(ex.getPatient().getUidn().equals(uidn) && ex.getDermatologist().getUidn().equals(dermuidn) && ex.getDateRange().getStartDateTime().toString().equals(fullDate)) {
				ex.setAppointmentStatus(AppointmentStatus.UNATTENDED);
				Patient patient = findByUidn(ex.getPatient().getUidn());
				int penalty = patient.getPenalty();
				patient.setPenalty(++penalty);
				patientRepository.save(patient);
				return examRepository.save(ex);
			}
		}
		return null;
	}
	@Override
	public List<Patient> findAll() {
		return patientRepository.findAll();
	}
	@Override
	public Counseling pharmacistPenalize(String uidn) {
		List<Counseling> counseling = (List<Counseling>) counsService.findAll();
		for (Counseling coun : counseling) {
			if(coun.getPatient().getUidn().equals(uidn)) {
				coun.setAppointmentStatus(AppointmentStatus.UNATTENDED);
				Patient patient = findByUidn(coun.getPatient().getUidn());
				int penalty = patient.getPenalty();
				patient.setPenalty(++penalty);
				patientRepository.save(patient);
				return counsRepository.save(coun);
			}
		}
		return null;
	}

	@Override
	public PatientProfileDTO getPatientProfile() {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return new PatientProfileDTO(patient);
	}
	
	@Override
	public AllergiesDTO getPatientAllergies() {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		return new AllergiesDTO(patient);
	}
	@Override
	public PatientProfileDTO updatePatientProfile(PatientProfileDTO dto) {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		patient.setName(dto.getName());
		patient.setSurname(dto.getSurname());
		patient.setPhoneNumber(dto.getPhoneNumber());
		patient.getAddress().setCity(cityService.findById(dto.getCityId()));
		patient.getAddress().setStreet(dto.getStreet());
		patient.getAddress().setNumber(dto.getNumber());
		patient.getAddress().setLatitude(dto.getLatitude());
		patient.getAddress().setLongitude(dto.getLongitude());
		userService.save(patient);
		return dto;
	}
	
	@Override
	public AllergiesDTO updatePatientAllergies(AllergiesDTO dto) {
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Long> medicineIdList = new ArrayList<Long>();
		for(MedicineViewDTO medicine: dto.getAllergies()) {
			medicineIdList.add(medicine.getId());
		}
		List<Medicine> allergies = medicineService.findByMedicineIds(medicineIdList);
		patient.setAllergies(allergies);
		patientRepository.save(patient);
		return dto;
	}
	
	@Override
	public Boolean updatePatientPassword(PasswordUpdateDTO dto) throws PasswordControlException {
		if(!dto.getPassword().contentEquals(dto.getPasswordControl())) {
			throw new PasswordControlException("Passwords do not match.");
		}
		Patient patient = (Patient) userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		patient.setPassword(passwordEncoder.encode(dto.getPassword()));
		userService.save(patient);
		return Boolean.TRUE;
	}
	
	@Override
	public Patient changeUserCategory(Long userId, UserCategory userCategory) {
		Patient patient = (Patient) patientRepository.getOne(userId);
		patient.setUserCategory(userCategory);
		
		return patientRepository.save(patient);
	}
}
