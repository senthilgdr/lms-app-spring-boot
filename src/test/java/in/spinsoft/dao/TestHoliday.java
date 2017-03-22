package in.spinsoft.dao;

import java.time.LocalDate;
import java.util.List;

import in.spinsoft.service.HolidayService;

public class TestHoliday {

	public static void main(String[] args) {

		HolidayService holidayService = new HolidayService();
		/*
		 * List<Holiday> list = holidayService.list(); for (Holiday holiday :
		 * list) { System.out.println(holiday); }
		 */

		/*
		 * Holiday h=new Holiday(); h.setHolidayDate(LocalDate.now());
		 * h.setStatus("RAMJAN");
		 * 
		 * HolidayDAO dao=new HolidayDAO(); dao.addHoliday(h);
		 * 
		 * System.out.println("Added");
		 */

		/*
		 * Holiday holiday=holidayService.findById(13L);
		 * holiday.setHolidayDate(LocalDate.now());
		 * holiday.setStatus("Chrithumas"); holidayService.update(holiday);
		 * System.out.println("Updated");
		 */

		holidayService.delete(14L);
	}

}
