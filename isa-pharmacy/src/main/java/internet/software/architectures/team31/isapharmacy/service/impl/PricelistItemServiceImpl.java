package internet.software.architectures.team31.isapharmacy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.PriceListItemAppointment;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.PriceListItemMedicine;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.PriceListItemStatus;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.PricelistItem;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;
import internet.software.architectures.team31.isapharmacy.dto.PriceListItemAppointmentCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.PriceListItemMedicineCreateDTO;
import internet.software.architectures.team31.isapharmacy.repository.PricelistItemRepository;
import internet.software.architectures.team31.isapharmacy.service.MedicineService;
import internet.software.architectures.team31.isapharmacy.service.PricelistItemService;

@Service
public class PricelistItemServiceImpl implements PricelistItemService {
	
	@Autowired
	private PricelistItemRepository pricelistItemRepository;
	@Autowired
	private MedicineService medicineService;
	
	@Override
	public PricelistItem setNewPrice(PriceListItemMedicineCreateDTO item,Long id) {
		PricelistItem oldItem=this.findById(id);
		oldItem.getInterval().setEndDateTime(item.getStartDateTime());
		oldItem.setStatus(PriceListItemStatus.EXPIRED);
		this.pricelistItemRepository.save(oldItem);
		return this.addNewMedicineItem(item);
	}

	@Override
	public PricelistItem findById(Long id) {
		return this.pricelistItemRepository.findById(id).orElse(null) ;
	}


	@Override
	public PriceListItemMedicine addNewMedicineItem(PriceListItemMedicineCreateDTO dto) {
		PriceListItemMedicine item=new PriceListItemMedicine();
		item.setMedicine(this.medicineService.findById(dto.getMedicineId()));
		item.setInterval(new DateRange());
		item.getInterval().setStartDateTime(dto.getStartDateTime());
		item.getInterval().setEndDateTime(null);
		item.setStatus(PriceListItemStatus.ACTIVE);
		item.setPrice(dto.getPrice());
		return this.pricelistItemRepository.save(item);
	}

	@Override
	public List<PricelistItem> findAllActiveMedicineItem(Long pricelistId) {
		return this.pricelistItemRepository.findAllActiveMedicineItem(pricelistId);
	}

	@Override
	public List<PricelistItem> findAllActiveAppointmentItem(Long pricelistId) {
		return this.pricelistItemRepository.findAllActiveAppointmentItem(pricelistId);
	}

	@Override
	public PricelistItem addNewAppointmentItem(PriceListItemAppointmentCreateDTO dto) {
		PriceListItemAppointment item=new PriceListItemAppointment();
		item.setAppointmentType(dto.getAppointmentType());
		item.setInterval(new DateRange());
		item.getInterval().setStartDateTime(dto.getStartDateTime());
		item.getInterval().setEndDateTime(null);
		item.setStatus(PriceListItemStatus.ACTIVE);
		item.setPrice(dto.getPrice());
		return this.pricelistItemRepository.save(item);
	}

	@Override
	public PricelistItem setNewAppointmentPrice(PriceListItemAppointmentCreateDTO item, Long id) {
		PricelistItem oldItem=this.findById(id);
		oldItem.getInterval().setEndDateTime(item.getStartDateTime());
		oldItem.setStatus(PriceListItemStatus.EXPIRED);
		this.pricelistItemRepository.save(oldItem);
		return this.addNewAppointmentItem(item);
	}

}
