package bsh.uit.core.entities;

import java.time.*;

public class Datetime {
	
	//Date and time
	private LocalDate Date;
	private LocalTime Time;
	public LocalDate getDate() {
		return Date;
	}
	public void setDate(LocalDate date) {
		Date = date;
	}
	public LocalTime getTime() {
		return Time;
	}
	public void setTime(LocalTime time) {
		Time = time;
	}
}
