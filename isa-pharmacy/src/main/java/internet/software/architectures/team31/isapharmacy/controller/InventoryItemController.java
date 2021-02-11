package internet.software.architectures.team31.isapharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.InventoryItem;
import internet.software.architectures.team31.isapharmacy.service.InventoryItemService;

@RestController
@RequestMapping(value = "api/inventory")
public class InventoryItemController {

	@Autowired
	private InventoryItemService inventoryItemService;
	
	@PostMapping("/add")
	public ResponseEntity<InventoryItem> addInventoryItem(InventoryItem item) {
		return new ResponseEntity<>(this.inventoryItemService.save(item),HttpStatus.CREATED);
	}
	
	
}
