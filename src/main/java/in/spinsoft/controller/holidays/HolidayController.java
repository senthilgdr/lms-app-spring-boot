package in.spinsoft.controller.holidays;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.spinsoft.model.Holiday;
import in.spinsoft.service.HolidayService;


@Controller
@RequestMapping("holidays")
public class HolidayController {
 
	@Autowired
	private HolidayService holidayService ;

	@GetMapping("/list")
	public String list(ModelMap modelMap, HttpSession session) throws Exception {
 
		try {

			List<Holiday> list = holidayService.list();
			System.out.println("list:"+list);
			modelMap.addAttribute("HOLIDAY_LIST", list);

			return "holiday/list";

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "/home";
		}
	}

	@GetMapping("/create")
	public String create() {
		return "holiday/add";
	}

	@GetMapping("/save")
	public String save(@RequestParam("holidayDate") String holidayDate, @RequestParam("reason") String reason,
			ModelMap modelMap, HttpSession session) throws Exception {

		try {

			// Step : Store in View
			Holiday holiday = new Holiday();
			holiday.setHolidayDate(LocalDate.parse(holidayDate));
			holiday.setStatus(reason);

			holidayService.save(holiday);

			return "redirect:list";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "add";
		}
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id, ModelMap modelMap) throws Exception {

		try {
			holidayService.delete(Long.valueOf(id));

			return "redirect:/holidays/list";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "holiday/list";
		}

	}

	@GetMapping("/edit")
	public String edit(@RequestParam("id") Long id, ModelMap modelMap) throws Exception {

		try {

			Holiday ld = holidayService.findById(id);
			modelMap.addAttribute("EDIT_HOLIDAY", ld);

			return "holiday/edit";

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "holiday/list";
		}

	}

	@GetMapping("/update")
	public String update(@RequestParam("id") Long id, @RequestParam("holidayDate") String holidayDate,
			@RequestParam("reason") String reason, ModelMap modelMap, HttpSession session) throws Exception {

		try {

			Holiday holiday = new Holiday();
			holiday.setId(id);
			holiday.setHolidayDate(LocalDate.parse(holidayDate));
			holiday.setStatus(reason);

			holidayService.update(holiday);

			return "redirect:/holidays/list";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "edit";
		}

	}

}
