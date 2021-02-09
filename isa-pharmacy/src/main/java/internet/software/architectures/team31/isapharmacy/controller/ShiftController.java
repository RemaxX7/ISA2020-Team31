package internet.software.architectures.team31.isapharmacy.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.schedule.Shift;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;
import internet.software.architectures.team31.isapharmacy.dto.ShiftCreateDTO;
import internet.software.architectures.team31.isapharmacy.service.ShiftService;

@RestController
@RequestMapping(value = "api/shift")
public class ShiftController {
	
	@Autowired
	private ShiftService shiftService;
	
	@PostMapping(value="/add")
	public ResponseEntity<Shift> save(@RequestBody ShiftCreateDTO dto)
	{
		return new ResponseEntity<>(shiftService.save(dto),HttpStatus.CREATED);
	}
	@GetMapping(value = "/all/employee/{id}")
	 ResponseEntity<List<Shift>> findAllByEmployee(@PathVariable Long id){
		return  new ResponseEntity<>(this.shiftService.findAllByEmployeeId(id),HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/all/pharmacy/{id}")
	 ResponseEntity<List<Shift>> findAllByPharmacy(@PathVariable Long id){
		return new  ResponseEntity<>(this.shiftService.findAllByPharmacyId(id),HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/date/pharmacy/{id}")
	 ResponseEntity<List<Shift>> findAllByIntervalAndPharmacyId(@RequestBody DateRange interval,
			 @PathVariable Long id) {
		return new  ResponseEntity<>(shiftService.findAllByIntervalAndPharmacyId(interval.getStartDateTime(), interval.getEndDateTime(), id),HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/date/employee/{id}")
	 ResponseEntity<List<Shift>> findAllByIntervalAndEmployeeId(@RequestBody DateRange interval,
			 @PathVariable Long id) {
		return new  ResponseEntity<>(shiftService.findAllByIntervalAndEmployeeId(interval.getStartDateTime(), interval.getEndDateTime(), id),HttpStatus.OK);
	}
}
