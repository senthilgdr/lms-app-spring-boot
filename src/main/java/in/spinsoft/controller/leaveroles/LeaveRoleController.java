package in.spinsoft.controller.leaveroles;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.spinsoft.model.Employee;
import in.spinsoft.model.LeaveRole;
import in.spinsoft.service.LeaveRoleService;

@Controller
@RequestMapping("leaveroles")
public class LeaveRoleController {
	@Autowired
	private LeaveRoleService leaveRoleService;

	@GetMapping("/list")
	public String list(ModelMap modelMap, HttpSession session) throws Exception {

		try {

			List<LeaveRole> list = leaveRoleService.list();
			System.out.println(list);
			modelMap.addAttribute("LEAVE_ROLE_LIST", list);

			return "leaverole/list";

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "/home";
		}
	}

	@GetMapping("/myLeaveRole")
	public String myLeaveRole(ModelMap modelMap, HttpSession session) throws Exception {

		try {
			Employee employee = (Employee) session.getAttribute("LOGGED_IN_USER");

			LeaveRole leaveRole = leaveRoleService.findByRoleId(employee.getRole().getId());
			System.out.println(leaveRole);
			modelMap.addAttribute("LEAVE_ROLE", leaveRole);

			return "leaverole/myLeaveRoleList";

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "/home";
		}
	}

}
