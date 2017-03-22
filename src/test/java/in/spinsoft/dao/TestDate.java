package in.spinsoft.dao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TestDate {
	public static void main(String[] args) {
		LocalDate fromDate = LocalDate.parse("2017-05-12");
		LocalDate endDate = LocalDate.parse("2017-05-12");

		long daysBetween = ChronoUnit.DAYS.between(fromDate, endDate) + 1;
		System.out.println(daysBetween);

	}

}
