package in.spinsoft.controller.employee;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.spinsoft.dao.EmployeeDAO;
import in.spinsoft.dao.UserMailManager;
import in.spinsoft.model.Employee;
import in.spinsoft.model.EmployeeJobHistory;
import in.spinsoft.model.Role;
import in.spinsoft.service.EmployeeJobService;
import in.spinsoft.service.EmployeeService;

@Controller
@RequestMapping("employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	EmployeeJobService jobService;
	
	@Autowired
	EmployeeDAO employeeDAO;
	

	@PostMapping("/login")
	public String login(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			ModelMap modelMap, HttpSession session) {
		System.out.println("EmployeeController->login");

		Employee employee = employeeDAO.findByEmailIdAndPassword(emailId, password);
		if (employee != null) {
			session.setAttribute("LOGGED_IN_USER", employee);

			return "redirect:/home"; 
		} else {
			modelMap.addAttribute("ERROR_MESSAGE", "Invalid EmailID/Password");
			return "/index";
		}
	}

	@GetMapping("/Logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/register")
	public String register(@RequestParam("code") String code, @RequestParam("name") String name,
			@RequestParam("gender") String gender, @RequestParam("role") Long role,
			@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			@RequestParam("mobileNo") Long mobileNo, ModelMap modelMap, HttpSession session) throws Exception {

		try {
			Employee emp = new Employee();
			emp.setCode(code);
			emp.setName(name);
			emp.setGender(gender);
			emp.setEmailId(emailId);
			emp.setPassword(password);
			emp.setMobileNo(mobileNo);

			Role r = new Role();
			r.setId(role); // employeee
			emp.setRole(r);
			System.out.println(emp);

			employeeService.register(emp);

			UserMailManager.sendNewRegistrationEmail(emp);
			return "redirect:../";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("ERROR_MESSAGE", e.getMessage());
			return "employee/register";
		}

	}

	@GetMapping("/list")
	public String list(ModelMap modelMap, HttpSession session) throws Exception {

		try {

			List<Employee> list = employeeService.list();
			System.out.println(list);
			modelMap.addAttribute("EMPLOYEE_LIST", list);

			return "employee/list";

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("ERROR_MESSAGE", e.getMessage());
			return "/home";
		}
	}
	
	

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id, ModelMap modelMap) throws Exception {

		try {
			employeeService.delete(Long.valueOf(id));

			return "redirect:/employees/list";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("ERROR_MESSAGE", e.getMessage());
			return "employee/list";
		}
	}

	@GetMapping("/getForgotpassword")
	public String forgotpassword() {
		System.out.println("Employee->ForgotPassword");
		return "employee/forgotpassword";
	}

	@PostMapping("/forgotPassword")
	public String forgotPassword(@RequestParam("emailId") String emailId, ModelMap modelMap) throws Exception {

		try {

			employeeService.forgotPassword(emailId);

			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("ERROR_MESSAGE", e.getMessage());
			return "forgotpassword";
		}

	}

	@GetMapping("/changepassword")
	public String changepassword() {
		return "employee/changepassword";
	}

	@PostMapping("/updatePassword")
	public String changePassword(@RequestParam("currentpassword") String currentpassword,
			@RequestParam("newpassword") String newpassword, ModelMap modelMap, HttpSession session) throws Exception {

		try {
			Employee emp = (Employee) session.getAttribute("LOGGED_IN_USER");
			System.out.println("UpdatePassword:" + emp);
			employeeService.changePassword(emp.getEmailId(), currentpassword, newpassword);
			session.invalidate();
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("ERROR_MESSAGE", e.getMessage());
			return "index";
		}

	}
	
	@GetMapping("/myProfile")
	public String showMyprofile(ModelMap modelMap, HttpSession session) throws Exception {

		try {
			Employee emp = (Employee) session.getAttribute("LOGGED_IN_USER");
			
			EmployeeJobHistory jobHistory = jobService.listMyJob(emp.getId());
			modelMap.addAttribute("EMPLOYEE_JOB_DETAIL", jobHistory);

			return "employee/profile";

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("ERROR_MESSAGE", e.getMessage());
			return "/home";
		}
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("id") Long id, ModelMap modelMap, HttpSession session) throws Exception {

		try {

			Employee emp = (Employee) session.getAttribute("LOGGED_IN_USER");
			modelMap.addAttribute("EDIT_EMPLOYEE", emp);

			EmployeeJobHistory jobHistory = jobService.listMyJob(emp.getId());
			modelMap.addAttribute("EMPLOYEE_JOB_DETAIL", jobHistory);

			return "employee/edit";

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("ERROR_MESSAGE", e.getMessage());
			return "employee/list";
		}

	}

	@GetMapping("/update")
	public String update(@RequestParam("id") Long id, @RequestParam("code") String code,
			@RequestParam("name") String name, @RequestParam("role") Long role, @RequestParam("mailId") String mailId,
			@RequestParam("mobileNo") Long mobileNo, ModelMap modelMap, HttpSession session) throws Exception {

		try {
			Employee employee = employeeService.findById(id);
			employee.setCode(code);
			employee.setName(name);
			employee.setEmailId(mailId);
			employee.setMobileNo(mobileNo);

			Role r = new Role();
			r.setId(role);

			employee.setRole(r);
			employeeService.update(employee);

			Employee employeeNew = employeeService.findById(id);
			session.setAttribute("LOGGED_IN_USER", employeeNew);

			System.out.println("Updated" + employee);

			return "redirect:/employees/myProfile";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("ERROR_MESSAGE", e.getMessage());
			return "edit";
		}

	}

}
