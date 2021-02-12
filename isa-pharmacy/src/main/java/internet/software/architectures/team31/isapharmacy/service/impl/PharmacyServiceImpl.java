package internet.software.architectures.team31.isapharmacy.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.InventoryItem;
import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;

import internet.software.architectures.team31.isapharmacy.domain.schedule.Shift;

import internet.software.architectures.team31.isapharmacy.dto.InventoryItemCreateDTO;

import internet.software.architectures.team31.isapharmacy.dto.PharmacyViewDTO;
import internet.software.architectures.team31.isapharmacy.repository.PharmacyRepository;
import internet.software.architectures.team31.isapharmacy.service.CounselingService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyReviewService;
import internet.software.architectures.team31.isapharmacy.service.PharmacyService;
import internet.software.architectures.team31.isapharmacy.service.ShiftService;
import internet.software.architectures.team31.isapharmacy.service.InventoryItemService;

@Service
public class PharmacyServiceImpl implements PharmacyService {

	@Autowired
	private PharmacyRepository pharmacyRepository;

	@Autowired
	private PharmacyReviewService pharmacyReviewService;
	
	@Autowired
	private ShiftService shiftService;
	
	@Autowired
	private CounselingService counselingService;
	private InventoryItemService inventoryItemService;

	@Override
	public Pharmacy save(Pharmacy pharmacy) {
		return this.pharmacyRepository.save(pharmacy);
	}

	@Override
	public Collection<Pharmacy> findAll() {
		return pharmacyRepository.findAll(Sort.by("name"));
	}

	@Override
	public Page<PharmacyViewDTO> findAll(Pageable pageable) {
		Page<PharmacyViewDTO> pharmacies = pharmacyRepository.findAll(pageable).map(pharmacy -> new PharmacyViewDTO(pharmacy));
		pharmacies.forEach(pharmacy -> pharmacy.setRate(pharmacyReviewService.calculatePharmacyScore(pharmacy.getId())));
		return pharmacies;
	}
	
	@Override
	public Page<PharmacyViewDTO> search(String query, Pageable pageable) {
		Page<PharmacyViewDTO> pharmacies = pharmacyRepository.search(query, pageable).map(pharmacy -> new PharmacyViewDTO(pharmacy));
		pharmacies.forEach(pharmacy -> pharmacy.setRate(pharmacyReviewService.calculatePharmacyScore(pharmacy.getId())));
		return pharmacies;
	}

	@Override
	public Pharmacy findById(Long id) {
		return pharmacyRepository.findById(id).orElse(null);
	}

	@Override

	public Collection<PharmacyViewDTO> findAllAvailableForCounseling(LocalDateTime dateTime) {
		Collection<Shift> shifts = shiftService.findAllByDate(dateTime);
		Collection<PharmacyViewDTO> pharmacies = new ArrayList<PharmacyViewDTO>();
		for(Shift shift: shifts) {
			if(counselingService.areThereAvailablePharmacists(shift, dateTime)) {
				pharmacies.add(new PharmacyViewDTO(shift.getPharmacy()));
			}
		}
		return pharmacies;
	}

	public Pharmacy addNewItem(InventoryItemCreateDTO dto) {
		Pharmacy pharmacy= this.findById(dto.getPharmacyId());
		pharmacy.getInventory().add(this.inventoryItemService.addNewItem(dto));
		return this.save(pharmacy);
	}
}
