package internet.software.architectures.team31.isapharmacy.domain.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DateRange {

	@Column(name = "start_date_time")
	private LocalDateTime startDateTime;
	@Column(name = "end_date_time")
	private LocalDateTime endDateTime;
	
	public DateRange() {
		super();
	}

	public DateRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		super();
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}

	public boolean includes(LocalDateTime date) {
		return !date.isBefore(startDateTime) && !date.isAfter(endDateTime);
	}
	
	public boolean includes(DateRange dateRange) {
		return this.includes(dateRange.startDateTime) && this.includes(dateRange.endDateTime);
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	public static long getMinutesBetween(DateRange range) {
		return Duration.between(range.getStartDateTime(), range.getEndDateTime()).toMinutes();
	}
}
