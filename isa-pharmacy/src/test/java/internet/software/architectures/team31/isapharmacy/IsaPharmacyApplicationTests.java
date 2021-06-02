package internet.software.architectures.team31.isapharmacy;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import internet.software.architectures.team31.isapharmacy.domain.patient.Exam;
import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservation;
import internet.software.architectures.team31.isapharmacy.domain.patient.MedicineReservationStatus;
import internet.software.architectures.team31.isapharmacy.domain.users.Dermatologist;
import internet.software.architectures.team31.isapharmacy.domain.users.User;
import internet.software.architectures.team31.isapharmacy.service.ExamService;
import internet.software.architectures.team31.isapharmacy.service.MedicineReservationService;
import internet.software.architectures.team31.isapharmacy.service.UserService;

@SpringBootTest
class IsaPharmacyApplicationTests {
	private static final int DB_USER_QUANT = 7;
	private static final String DERM_ID = "3";
	private static final int TOTAL_EXAMS_WITH_DERMATOLOGIST_ID3 = 11;
	private static final String RESERVATION_STATUS_QUANT = "2";
	
	@Mock
	private UserService userService;
	
	@Mock
	private ExamService examService;
	
	@Mock
	private MedicineReservationService medicineReservationService;
	
	@Test
	public void FindAllUsers() {
		List<User> userList = userService.findAll();
		assertTrue(userList.size()==DB_USER_QUANT);
	}
	
	@Test
	public void GetAllExams() {
		List<Exam>examList = (List<Exam>) examService.findAllByOrderByStartDateTimeAsc();
		assertNotNull(examList);
		assertTrue(!examList.isEmpty());
	}
	
	@Test
	public void GetSpecificDermatologist() {
		Dermatologist derm = (Dermatologist) userService.findByUidn("3234567891234");
		assertTrue(derm.getId() == Integer.parseInt(DERM_ID));
	}
	
	@Test
	public void TotalNumberOfDermatologistScheduledAppointments() {
		List<Exam>examList = (List<Exam>) examService.findAllByOrderByStartDateTimeAsc();
		int needed = 0;
		for (Exam exam : examList) {
			if(exam.getDermatologist().getId().equals(Long.parseLong(DERM_ID)))
				++needed;
		}
		assertTrue(needed == TOTAL_EXAMS_WITH_DERMATOLOGIST_ID3);
	}
	
	@Test
	public void ActiveMedicineReservations() {
		List<MedicineReservation> medicineReservationsList = (List<MedicineReservation>) medicineReservationService.findAll();
		int needed = 0;
		for (MedicineReservation medicineReservation : medicineReservationsList) {
			if(medicineReservation.getMedicineReservationStatus().equals(MedicineReservationStatus.CREATED))
				++needed;
		}
		assertTrue(needed == Integer.parseInt(RESERVATION_STATUS_QUANT));
	}
}
