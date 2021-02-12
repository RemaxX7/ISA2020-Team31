package internet.software.architectures.team31.isapharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.InventoryItem;

public interface InventoryItemRepository  extends JpaRepository<InventoryItem, Long> {
}
