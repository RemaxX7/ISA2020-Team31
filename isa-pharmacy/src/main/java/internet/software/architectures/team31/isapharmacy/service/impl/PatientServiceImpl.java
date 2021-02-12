package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.patient.AppointmentStatus;
import internet.software.architectures.team31.isapharmacy.domain.patient.Counseling;
import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.domain.patient.UserCategory;
import internet.software.architectures.team31.isapharmacy.domain.users.Patient;
import internet.software.architectures.team31.isapharmacy.repository.CounselingRepository;
import internet.software.architectures.team31.isapharmacy.repository.ExamRepository;
import internet.software.architectures.team31.isapharmacy.repository.PatientRepository;
import internet.software.architectures.team31.isapharmacy.service.PatientService;
@Service
public class PatientServiceImpl implements PatientService{

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
	@Override
	public Patient findByUidn(String uidn) {
		return patientRepository.findByUidn(uidn);
	}
	@Override
	public Exam penalize(String uidn){
		List<Exam> exam = (List<Exam>) examService.findAll();
		for (Exam ex : exam) {
			if(ex.getPatient().getUidn().equals(uidn)) {
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
	public Patient changeUserCategory(Long userId, UserCategory userCategory) {
		Patient patient = (Patient) patientRepository.getOne(userId);
		patient.setUserCategory(userCategory);
		
		return patientRepository.save(patient);
	}
}
