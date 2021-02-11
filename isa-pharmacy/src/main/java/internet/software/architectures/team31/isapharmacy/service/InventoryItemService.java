package internet.software.architectures.team31.isapharmacy.service;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.InventoryItem;

public interface InventoryItemService {
	InventoryItem save(InventoryItem inventoryItem);
	InventoryItem findById(Long id);
	InventoryItem addQuantity(Long itemId,Double quantity);
	InventoryItem subQuantity(Long itemId,Double quantity);	
	//InventoryItem findByPharmacyId(Long id);

}
