package internet.software.architectures.team31.isapharmacy.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.schedule.Shift;
import internet.software.architectures.team31.isapharmacy.domain.users.Employee;
import internet.software.architectures.team31.isapharmacy.dto.ExamCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.ShiftCreateDTO;
import internet.software.architectures.team31.isapharmacy.exception.ShiftNotFreeEception;
import internet.software.architectures.team31.isapharmacy.repository.ShiftRepository;
import internet.software.architectures.team31.isapharmacy.service.ExamService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;
import internet.software.architectures.team31.isapharmacy.service.ShiftService;
import internet.software.architectures.team31.isapharmacy.service.UserService;


@Service
public class ShiftServiceImpl implements ShiftService {
	
	@Autowired
	private ShiftRepository shiftRepository;
	@Autowired
	private PharmacyService pharmacyService;
	@Autowired
	private UserService userService;
	@Autowired
	private ExamService examService;
	
	@Override
	public List<Shift> findAllByEmployeeId(Long id) {
		return shiftRepository.findAllByEmployeeId(id);
	}
	@Override
	public List<Shift> findAllByEmployeeUidn(String uidn) {
		return shiftRepository.findAllByEmployeeUidn(uidn);
	}
	
	@Override
	public List<Shift> findAllByPharmacyId(Long id) {
		return shiftRepository.findAllByPharmacyId(id);
	}


	@Override
	public Shift save(ShiftCreateDTO shift) throws ShiftNotFreeEception  {

		Shift s=new Shift(shift);
		s.setPharmacy(pharmacyService.findById(shift.getPharmacyId()));
		s.setEmployee((Employee) userService.findById(shift.getEmployeeId()));
		if(!this.findAllByIntervalAndEmployeeId(shift.getStartDateTime(), shift.getEndDateTime(), shift.getEmployeeId()).isEmpty())
		{
			throw new  ShiftNotFreeEception("Employee already has a shift at the choosen time interval");
		}
		if(userService.findTypeById(shift.getEmployeeId()).compareToIgnoreCase("Dermatologist")==0)
		{
			addExams(shift, s);
			
		}
		return shiftRepository.save(s);
	}


	private void addExams(ShiftCreateDTO shift, Shift s) {
		LocalDateTime startDate=s.getInterval().getStartDateTime();
		ExamCreateDTO dtoEx=new ExamCreateDTO();
		dtoEx.setDermatologistId(shift.getEmployeeId());
		dtoEx.setPharmacyId(shift.getPharmacyId());
		dtoEx.setPrice(400.00);
		while(startDate.compareTo(s.getInterval().getEndDateTime())<=0) {
			dtoEx.setStartDateTime(startDate);
			startDate=startDate.plusMinutes(20);
			dtoEx.setEndDateTime(startDate);
			examService.save(dtoEx);
		};
	}

	@Override
	public List<Shift> findAllByIntervalAndPharmacyId(LocalDateTime startDateTime, LocalDateTime endDateTime,
			Long pharmachyId) {
		return this.shiftRepository.findAllByIntervalAndPharmacyId(startDateTime, endDateTime, pharmachyId);
	}


	@Override
	public List<Shift> findAllByIntervalAndEmployeeId(LocalDateTime startDateTime, LocalDateTime endDateTime, Long id) {
		return this.shiftRepository.findAllByIntervalAndEmployeeId(startDateTime, endDateTime, id);
	}

	@Override
	public Collection<Shift> findAllByDate(LocalDateTime dateTime) {
		return shiftRepository.findAllByDate(dateTime);
	}
	
	@Override
	public Collection<Shift> findAllByDateAndPharmacyId(LocalDateTime dateTime, Long pharmacyId) {
		return shiftRepository.findAllByDateAndPharmacyId(dateTime, pharmacyId);
	}
}
