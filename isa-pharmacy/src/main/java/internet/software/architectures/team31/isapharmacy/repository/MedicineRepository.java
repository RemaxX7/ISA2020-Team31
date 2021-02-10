package internet.software.architectures.team31.isapharmacy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

	Medicine findAllByName(String name);
	List<Medicine> findAll();
	Medicine findByName(String name);
	
}
