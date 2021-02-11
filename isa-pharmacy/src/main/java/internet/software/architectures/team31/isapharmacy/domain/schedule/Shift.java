package internet.software.architectures.team31.isapharmacy.domain.schedule;


import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import internet.software.architectures.team31.isapharmacy.domain.pharmacy.Pharmacy;
import internet.software.architectures.team31.isapharmacy.domain.users.Employee;
import internet.software.architectures.team31.isapharmacy.domain.util.DateRange;
import internet.software.architectures.team31.isapharmacy.dto.ShiftCreateDTO;

@Entity
@Table(name = "shifts")
public class Shift {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		private Employee employee;
		
		@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		private Pharmacy pharmacy;
		
		@Embedded
		private DateRange interval;

		public Shift(Long id,Employee employee,Pharmacy pharmacy, DateRange interval) {
			super();
			this.id = id;
			this.employee = employee;
			this.pharmacy = pharmacy;
			this.interval = interval;
		}
		
		public Shift() {}


		public Shift(ShiftCreateDTO shift) {
			this.interval = new DateRange(shift.getStartDateTime(), shift.getEndDateTime());
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}

		public Pharmacy getPharmacy() {
			return pharmacy;
		}

		public void setPharmacy(Pharmacy pharmacy) {
			this.pharmacy = pharmacy;
		}

		public DateRange getInterval() {
			return interval;
		}

		public void setInterval(DateRange interval) {
			this.interval = interval;
		}
		
}
