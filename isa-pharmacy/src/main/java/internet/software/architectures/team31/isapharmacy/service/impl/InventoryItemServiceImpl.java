package internet.software.architectures.team31.isapharmacy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.InventoryItem;
import internet.software.architectures.team31.isapharmacy.dto.InventoryItemCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.SetQuantityDTO;
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
	public InventoryItem addQuantity(SetQuantityDTO dto) {
		InventoryItem item=findById(dto.getItemId());
		item.setQuantity(item.getQuantity()+dto.getQuantity());
		return inventoryItemRepository.save(item);
	}

	@Override
	public InventoryItem subQuantity(SetQuantityDTO dto) {
		InventoryItem item=findById(dto.getItemId());
		item.setQuantity(item.getQuantity()-dto.getQuantity());
		return inventoryItemRepository.save(item);
	}
	
	@Override
	public InventoryItem setQuantity(SetQuantityDTO dto) {
		InventoryItem item=findById(dto.getItemId());
		item.setQuantity(dto.getQuantity());
		return inventoryItemRepository.save(item);
	}
	

	@Override
	public InventoryItem addNewItem(InventoryItemCreateDTO dto) {
		InventoryItem item=new InventoryItem();
		item.setMedicine(this.medicineService.findById(dto.getMedicineId()));
		item.setQuantity(dto.getQuantity());
		return this.save(item);
	}
	
}
