package internet.software.architectures.team31.isapharmacy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import internet.software.architectures.team31.isapharmacy.domain.medicine.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

	Medicine findAllByName(String name);
	List<Medicine> findAll();
	Medicine findByName(String name);
	
	@Query("SELECT m FROM Medicine m WHERE LOWER(m.name) LIKE LOWER(concat('%', ?1,'%'))")
	Page<Medicine> search(String query, Pageable pageable);
	
	@Query( "SELECT m from Medicine m WHERE m.id in :ids" )
	List<Medicine> findByMedicineIds(@Param("ids") List<Long> medicineIdList);
}
