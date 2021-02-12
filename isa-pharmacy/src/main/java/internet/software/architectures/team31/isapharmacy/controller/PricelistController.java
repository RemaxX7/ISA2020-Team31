package internet.software.architectures.team31.isapharmacy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.PriceListItemMedicine;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pricelist;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.PricelistItem;
import internet.software.architectures.team31.isapharmacy.dto.PriceListItemAppointmentCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.PriceListItemMedicineCreateDTO;
import internet.software.architectures.team31.isapharmacy.service.PricelistItemService;
import internet.software.architectures.team31.isapharmacy.service.PricelistService;

@RestController
@RequestMapping(value = "auth/pricelist")
public class PricelistController {
	
	@Autowired
	private PricelistService pricelistService;
	
	@PostMapping(value="/add")
	public ResponseEntity<Pricelist> save(@RequestBody Pricelist pricelist)
	{
		return new ResponseEntity<>(pricelistService.addPricelist(pricelist),HttpStatus.CREATED);
	}
	
	@PutMapping(value="item/add/{id}")
	public ResponseEntity<Pricelist> save(@RequestBody PriceListItemMedicineCreateDTO dto,@PathVariable Long id)
	{
		return new ResponseEntity<>(pricelistService.addItem(dto,id),HttpStatus.CREATED);
	}
	
	@PutMapping(value="item/setNewPrice/{id}/{itemId}")
	public ResponseEntity<Pricelist> setNewPrice(@RequestBody PriceListItemMedicineCreateDTO dto,@PathVariable Long id,@PathVariable Long itemId){
		return new ResponseEntity<>(pricelistService.setNewPrice(dto,id,itemId),HttpStatus.OK);
	}
	
	@GetMapping(value="{id}/allMedicine")
	public ResponseEntity<List<PricelistItem>> findAllActiveMedicineItem(@PathVariable Long id){
		return new ResponseEntity<>(pricelistService.findAllActiveMedicineItem(id),HttpStatus.OK);
	}
	
	@PutMapping(value="item/add/appointment/{id}")
	public ResponseEntity<Pricelist> save(@RequestBody PriceListItemAppointmentCreateDTO dto,@PathVariable Long id)
	{
		return new ResponseEntity<>(pricelistService.addNewAppointmentItem(dto,id),HttpStatus.CREATED);
	}
	
	@PutMapping(value="item/setNewPrice/appointment/{id}/{itemId}")
	public ResponseEntity<Pricelist> setNewPrice(@RequestBody PriceListItemAppointmentCreateDTO dto,@PathVariable Long id,@PathVariable Long itemId){
		return new ResponseEntity<>(pricelistService.setNewAppointmentPrice(dto,id,itemId),HttpStatus.OK);
	}
	
	@GetMapping(value="{id}/allAppointment")
	public ResponseEntity<List<PricelistItem>> findAllActiveAppointmentItem(@PathVariable Long id){
		return new ResponseEntity<>(pricelistService.findAllActiveAppointmentItem(id),HttpStatus.OK);
	}
	
	
}
