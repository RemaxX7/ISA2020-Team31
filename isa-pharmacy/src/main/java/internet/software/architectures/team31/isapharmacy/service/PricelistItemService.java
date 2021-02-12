package internet.software.architectures.team31.isapharmacy.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.PriceListItemMedicine;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.PricelistItem;
import internet.software.architectures.team31.isapharmacy.dto.PriceListItemAppointmentCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.PriceListItemMedicineCreateDTO;

public interface PricelistItemService {

	PricelistItem findById(Long id);
	PricelistItem addNewMedicineItem(PriceListItemMedicineCreateDTO dto);
	PricelistItem setNewPrice(PriceListItemMedicineCreateDTO item, Long id);
	List<PricelistItem> findAllActiveAppointmentItem(Long pricelistId);
	PricelistItem addNewAppointmentItem(PriceListItemAppointmentCreateDTO dto);
	PricelistItem setNewAppointmentPrice(PriceListItemAppointmentCreateDTO item, Long id);
	List<PricelistItem> findAllActiveMedicineItem(Long pricelistId);
}
