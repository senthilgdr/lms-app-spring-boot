package in.spinsoft.controller.employee;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.spinsoft.dao.EmployeeHierarchyDAO;
import in.spinsoft.model.Employee;
import in.spinsoft.model.EmployeeHierarchy;
import in.spinsoft.service.EmployeeHierarchyService;

@Controller
@RequestMapping("employeesHierarchy")
public class EmployeeHierarchyController {
	@Autowired
	EmployeeHierarchyService employeeHierarchyService;

	@GetMapping("/create")
	public String create() {
		return "employeeHierarchy/add";
	}

	@GetMapping("/save")
	public String save(@RequestParam("empId") Long empId, @RequestParam("mgrId") Long mgrId, ModelMap modelMap,
			HttpSession session) throws Exception {

		try {

			EmployeeHierarchy employeeHierarchy = new EmployeeHierarchy();

			Employee employee = new Employee();
			employee.setId(empId);

			employeeHierarchy.setEmployee(employee);

			Employee manager = new Employee();
			manager.setId(mgrId);

			employeeHierarchy.setManager(manager);

			employeeHierarchyService.insert(employeeHierarchy);
			System.out.println(employeeHierarchy);

			return "redirect:list";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "add";
		}

	}

	@GetMapping("/list")
	public String list(ModelMap modelMap, HttpSession session) throws Exception {

		try {

			List<EmployeeHierarchy> list = employeeHierarchyService.list();
			System.out.println(list);
			modelMap.addAttribute("EMPLOYEE_HIERARCHY_LIST", list);

			return "employeeHierarchy/list";

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "/home";
		}
	}

	@GetMapping("/listMyTeam")
	public String listMyTeam(ModelMap modelMap, HttpSession session) throws Exception {

		try {
			Employee employee = (Employee) session.getAttribute("LOGGED_IN_USER");
			List<EmployeeHierarchy> list = employeeHierarchyService.listMyTeam(employee.getId());
			System.out.println(list);
			modelMap.addAttribute("EMPLOYEE_HIERARCHY_LIST", list);

			return "employeeHierarchy/list";

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "/home";
		}
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("id") Long id, ModelMap modelMap) throws Exception {

		try {

			EmployeeHierarchy ld = new EmployeeHierarchyDAO().findById(id);
			modelMap.addAttribute("EDIT_EMPLOYEE_HIERARCHY", ld);

			return "employeeHierarchy/edit";

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "employeeHierarchy/list";
		}

	}

	@GetMapping("/update")
	public String update(@RequestParam("id") Long id, @RequestParam("empId") Long empId,
			@RequestParam("mgrId") Long mgrId, ModelMap modelMap, HttpSession session) throws Exception {

		try {

			// Step : Store in View
			EmployeeHierarchy employeeHierarchy = employeeHierarchyService.findById(id);

			Employee employee = new Employee();
			employee.setId(empId);

			employeeHierarchy.setEmployee(employee);

			Employee manager = new Employee();
			manager.setId(mgrId);

			employeeHierarchy.setManager(manager);
			employeeHierarchyService.update(employeeHierarchy);

			return "redirect:employeesHierarchy/list";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "edit";
		}

	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id, ModelMap modelMap) throws Exception {

		try {
			employeeHierarchyService.delete(Long.valueOf(id));

			return "redirect:employeesHierarchy/list";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "employeeHierarchy/list";
		}
	}
}
