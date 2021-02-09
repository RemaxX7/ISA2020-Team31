package internet.software.architectures.team31.isapharmacy.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.schedule.Shift;
import internet.software.architectures.team31.isapharmacy.domain.users.Employee;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;
import internet.software.architectures.team31.isapharmacy.dto.ShiftCreateDTO;
import internet.software.architectures.team31.isapharmacy.repository.ShiftRepository;
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



	@Override
	public List<Shift> findAllByEmployeeId(Long id) {
		return shiftRepository.findAllByEmployeeId(id);
	}


	@Override
	public List<Shift> findAllByPharmacyId(Long id) {
		return shiftRepository.findAllByPharmacyId(id);
	}


	@Override
	public Shift save(ShiftCreateDTO shift) {
		Shift s=new Shift(shift);
		s.setPharmacy(pharmacyService.findById(shift.getPharmacyId()));
		s.setEmployee((Employee) userService.findById(shift.getEmployeeId()));
		
		return shiftRepository.save(s);
	}


	@Override
	public List<Shift> findAllByIntervalAndPharmacyId(LocalDateTime startDateTime, LocalDateTime endDateTime,
			Long pharmachyId) {
		return this.shiftRepository.findAllByIntervalAndPharmacyId(startDateTime, endDateTime, pharmachyId);
	}


	@Override
	public List<Shift> findAllByIntervalAndEmployeeId(LocalDateTime startDateTime, LocalDateTime endDateTime,
			Long employeeId) {
		return this.findAllByIntervalAndEmployeeId(startDateTime, endDateTime, employeeId);
	}
	
}
