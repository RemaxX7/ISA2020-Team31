package internet.software.architectures.team31.isapharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.InventoryItem;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.dto.InventoryItemCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.SetQuantityDTO;
import internet.software.architectures.team31.isapharmacy.service.InventoryItemService;

@RestController
@RequestMapping(value = "auth/inventory")
public class InventoryItemController {

	@Autowired
	private InventoryItemService inventoryItemService;
	
	
	@PutMapping(value="/addQuantity")
	public ResponseEntity<InventoryItem> addInventoryItem(@RequestBody SetQuantityDTO dto) {
		return new ResponseEntity<>(this.inventoryItemService.addQuantity(dto),HttpStatus.OK);
	}
	
	@PutMapping(value="/subQuantity")
	public ResponseEntity<InventoryItem> subQuantity(@RequestBody SetQuantityDTO dto) {
		return new ResponseEntity<>(this.inventoryItemService.subQuantity(dto),HttpStatus.OK);
	}
	
	@PutMapping(value="/setQuantity")
	public ResponseEntity<InventoryItem> setQuantity(@RequestBody SetQuantityDTO dto) {
		return new ResponseEntity<>(this.inventoryItemService.setQuantity(dto),HttpStatus.OK);
	}
	
	
}
