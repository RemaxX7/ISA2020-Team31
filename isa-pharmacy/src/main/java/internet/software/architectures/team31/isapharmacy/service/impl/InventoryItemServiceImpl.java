package internet.software.architectures.team31.isapharmacy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.InventoryItem;
import internet.software.architectures.team31.isapharmacy.repository.InventoryItemRepository;
import internet.software.architectures.team31.isapharmacy.service.InventoryItemService;
import internet.software.architectures.team31.isapharmacy.service.MedicineService;

@Service
public class InventoryItemServiceImpl implements InventoryItemService{

	@Autowired
	private InventoryItemRepository inventoryItemRepository;
	
	@Autowired
	private MedicineService medicineService;
	
	@Override
	public InventoryItem save(InventoryItem inventoryItem) {
		return inventoryItemRepository.save(inventoryItem);
	}

	@Override
	public InventoryItem findById(Long id) {
		return inventoryItemRepository.findById(id).orElse(null);
	}

	@Override
	public InventoryItem addQuantity(Long itemId, Double quantity) {
		InventoryItem item=findById(itemId);
		item.setQuantity(item.getQuantity()+quantity);
		inventoryItemRepository.save(item);
		return item;
	}

	@Override
	public InventoryItem subQuantity(Long itemId, Double quantity) {
		InventoryItem item=findById(itemId);
		item.setQuantity(item.getQuantity()-quantity);
		inventoryItemRepository.save(item);
		return null;
	}
	
}
