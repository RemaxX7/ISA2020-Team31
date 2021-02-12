package internet.software.architectures.team31.isapharmacy.dto;

import java.util.List;
import java.util.stream.Collectors;

import internet.software.architectures.team31.isapharmacy.domain.users.Patient;

public class AllergiesDTO {

	private List<MedicineViewDTO> allergies;

	public AllergiesDTO() {
		super();
	}

	public AllergiesDTO(List<MedicineViewDTO> allergies) {
		super();
		this.allergies = allergies;
	}
	
	public AllergiesDTO(Patient patient) {
		super();
		this.allergies = patient.getAllergies().stream().map(medicine -> new MedicineViewDTO(medicine)).collect(Collectors.toList());
	}

	public List<MedicineViewDTO> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<MedicineViewDTO> allergies) {
		this.allergies = allergies;
	}
}
