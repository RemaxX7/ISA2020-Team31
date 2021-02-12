package internet.software.architectures.team31.isapharmacy.service;

import java.util.List;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.PriceListItemMedicine;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pricelist;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.PricelistItem;
import internet.software.architectures.team31.isapharmacy.dto.PriceListItemAppointmentCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.PriceListItemMedicineCreateDTO;

public interface PricelistService {
	Pricelist findByPharmacyId(Long id);
	Pricelist addPricelist(Pricelist pricelist);
	Pricelist addItem(PriceListItemMedicineCreateDTO dto,Long id);
	Pricelist setNewPrice(PriceListItemMedicineCreateDTO dto,Long id,Long itemId);
	List<PricelistItem> findAllActiveAppointmentItem(Long pricelistId);
	Pricelist addNewAppointmentItem(PriceListItemAppointmentCreateDTO dto,Long id);
	Pricelist setNewAppointmentPrice(PriceListItemAppointmentCreateDTO dto,Long id,Long itemId);
	List<PricelistItem> findAllActiveMedicineItem(Long pricelistId);
}
