package internet.software.architectures.team31.isapharmacy.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import internet.software.architectures.team31.isapharmacy.exception.AlreadyRepliedException;
import internet.software.architectures.team31.isapharmacy.exception.AppointmentNotFreeException;
import internet.software.architectures.team31.isapharmacy.exception.CancelAppointmentException;
import internet.software.architectures.team31.isapharmacy.exception.CancelMedicineReservationException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidComplaintException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidReviewException;
import internet.software.architectures.team31.isapharmacy.exception.InvalidScoreException;
import internet.software.architectures.team31.isapharmacy.exception.PenaltyException;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler  {

	@ExceptionHandler(value = PenaltyException.class)
	public ResponseEntity<Object> handlePenaltyException(PenaltyException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(value = CancelAppointmentException.class)
	public ResponseEntity<Object> handleCancelAppointmentException(CancelAppointmentException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = AppointmentNotFreeException.class)
	public ResponseEntity<Object> handleAppointmentNotFreeException(AppointmentNotFreeException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = InvalidComplaintException.class)
	public ResponseEntity<Object> handleInvalidComplaintException(InvalidComplaintException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = AlreadyRepliedException.class)
	public ResponseEntity<Object> handleAlreadyRepliedException(AlreadyRepliedException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = CancelMedicineReservationException.class)
	public ResponseEntity<Object> handleCancelMedicineReservationException(CancelMedicineReservationException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = InvalidScoreException.class)
	public ResponseEntity<Object> handleInvalidScoreException(InvalidScoreException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = InvalidReviewException.class)
	public ResponseEntity<Object> handleInvalidReviewException(InvalidReviewException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
