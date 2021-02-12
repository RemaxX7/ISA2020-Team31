package internet.software.architectures.team31.isapharmacy.service;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.dto.InventoryItemCreateDTO;
import internet.software.architectures.team31.isapharmacy.dto.PharmacyViewDTO;

public interface PharmacyService {

	Pharmacy save(Pharmacy pharmacy);
	Collection<Pharmacy> findAll();
	Page<PharmacyViewDTO> findAll(Pageable pageable);
	Page<PharmacyViewDTO> search(String query, Pageable pageable);
	Pharmacy findById(Long id);
	Collection<PharmacyViewDTO> findAllAvailableForCounseling(LocalDateTime dateTime);
	Pharmacy addNewItem(InventoryItemCreateDTO dto);
}
