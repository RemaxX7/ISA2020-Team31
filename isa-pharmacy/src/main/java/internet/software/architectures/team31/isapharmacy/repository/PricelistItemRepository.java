package internet.software.architectures.team31.isapharmacy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.PricelistItem;;

public interface PricelistItemRepository extends JpaRepository<PricelistItem, Long> {
	
	@Query(value = "SELECT * FROM"
			+ " pricelist_item pi,pricelists p,pricelists_items poveznik"
			+ "  WHERE p.id=:pricelistId AND p.id=poveznik.pricelist_id AND pi.id=poveznik.items_id AND pi.status=0 AND pi.type='Medicine'", nativeQuery = true )
	List<PricelistItem> findAllActiveMedicineItem(@Param("pricelistId") Long pricelistId);
	
	@Query(value = "SELECT * FROM"
			+ " pricelist_item pi,pricelists p,pricelists_items poveznik"
			+ "  WHERE p.id=:pricelistId AND p.id=poveznik.pricelist_id AND pi.id=poveznik.items_id AND pi.status=0 AND pi.type='Appointment'", nativeQuery = true )
	List<PricelistItem> findAllActiveAppointmentItem(@Param("pricelistId") Long pricelistId);


}
