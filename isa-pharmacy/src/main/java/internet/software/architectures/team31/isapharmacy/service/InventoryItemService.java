package internet.software.architectures.team31.isapharmacy.service;

import java.util.List;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.InventoryItem;
import internet.software.architectures.team31.isapharmacy.dto.InventoryItemCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.SetQuantityDTO;

public interface InventoryItemService {
	InventoryItem findById(Long id);
	InventoryItem save(InventoryItem inventoryItem);
	InventoryItem addQuantity(SetQuantityDTO dto);
	InventoryItem subQuantity(SetQuantityDTO dto);	
	//InventoryItem findByPharmacyId(Long id);
	InventoryItem addNewItem(InventoryItemCreateDTO dto);
	InventoryItem setQuantity(SetQuantityDTO dto);
	List<InventoryItem> findAll();

}
