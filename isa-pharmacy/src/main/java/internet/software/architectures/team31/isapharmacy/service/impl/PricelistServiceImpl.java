package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.PriceListItemMedicine;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pricelist;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.PricelistItem;
import internet.software.architectures.team31.isapharmacy.dto.PriceListItemAppointmentCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.PriceListItemMedicineCreateDTO;
import internet.software.architectures.team31.isapharmacy.repository.PricelistRepository;
import internet.software.architectures.team31.isapharmacy.service.MedicineService;
import internet.software.architectures.team31.isapharmacy.service.PricelistItemService;
import internet.software.architectures.team31.isapharmacy.service.PricelistService;

@Service
public class PricelistServiceImpl implements PricelistService {

	@Autowired
	private PricelistRepository pricelistRepository;
	@Autowired
	private PricelistItemService pricelistItemService;
	
	
	@Override
	public Pricelist addPricelist(Pricelist pricelist) {
		return this.pricelistRepository.save(pricelist);
	}
	
	
	@Override
	public Pricelist addItem(PriceListItemMedicineCreateDTO dto,Long id) {
		Pricelist pricelist=this.pricelistRepository.findById(id).orElse(null);
		List<PricelistItem> list=pricelist.getItems();
		list.add(this.pricelistItemService.addNewMedicineItem(dto));
		pricelist.setItems(list);
		return this.pricelistRepository.save(pricelist);
	}
	
	@Override
	public Pricelist setNewPrice(PriceListItemMedicineCreateDTO dto,Long id,Long itemId) {
		Pricelist pricelist=this.pricelistRepository.findById(id).orElse(null);
		List<PricelistItem> list=pricelist.getItems();
		list.add(this.pricelistItemService.setNewPrice(dto, itemId));
		pricelist.setItems(list);
		return this.pricelistRepository.save(pricelist);
	}


	@Override
	public List<PricelistItem> findAllActiveMedicineItem(Long pricelistId) {
		return this.pricelistItemService.findAllActiveMedicineItem(pricelistId);
	}


	@Override
	public List<PricelistItem> findAllActiveAppointmentItem(Long pricelistId) {
		return this.pricelistItemService.findAllActiveAppointmentItem(pricelistId);
	}


	@Override
	public Pricelist addNewAppointmentItem(PriceListItemAppointmentCreateDTO dto, Long id) {
		Pricelist pricelist=this.pricelistRepository.findById(id).orElse(null);
		List<PricelistItem> list=pricelist.getItems();
		list.add(this.pricelistItemService.addNewAppointmentItem(dto));
		pricelist.setItems(list);
		return this.pricelistRepository.save(pricelist);
	}


	@Override
	public Pricelist setNewAppointmentPrice(PriceListItemAppointmentCreateDTO dto, Long id, Long itemId) {
		Pricelist pricelist=this.pricelistRepository.findById(id).orElse(null);
		List<PricelistItem> list=pricelist.getItems();
		list.add(this.pricelistItemService.setNewAppointmentPrice(dto, itemId));
		pricelist.setItems(list);
		return this.pricelistRepository.save(pricelist);
	}
	
	

}
