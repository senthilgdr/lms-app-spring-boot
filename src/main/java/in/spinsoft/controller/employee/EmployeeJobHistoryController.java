package in.spinsoft.controller.employee;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.spinsoft.model.Employee;
import in.spinsoft.model.EmployeeJobHistory;
import in.spinsoft.service.EmployeeJobService;

@Controller
@RequestMapping("employeesJobHistory")
public class EmployeeJobHistoryController {
	@Autowired
	EmployeeJobService employeeJobHistoryService;

	
	@GetMapping("/save")
	public String save(@RequestParam("id") Long id, @RequestParam("designation") String designation,
			@RequestParam("department") String department, ModelMap modelMap, HttpSession session) throws Exception {

		try {
			EmployeeJobHistory empJobHistoryObj = new EmployeeJobHistory();

			Employee employee = (Employee) session.getAttribute("LOGGED_IN_USER");
			employee.setId(id);

			empJobHistoryObj.setId(id);
			empJobHistoryObj.setEmployee(employee);
			empJobHistoryObj.setDesignation(designation);
			empJobHistoryObj.setDepartment(department);

			employeeJobHistoryService.insert(empJobHistoryObj);

			return "redirect:/employees/myProfile";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("ERROR_MESSAGE", e.getMessage());
			return "add";
		}

	}

	@GetMapping("/list")
	public String list(ModelMap modelMap, HttpSession session) throws Exception {

		try {
			List<EmployeeJobHistory> list = employeeJobHistoryService.list();
			System.out.println(list);
			modelMap.addAttribute("EMPLOYEE_JOB_LIST", list);

			return "employee/jobhistorylist";

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("ERROR_MESSAGE", e.getMessage());
			return "/home";
		}
	}

}
