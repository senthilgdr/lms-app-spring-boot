package in.spinsoft.controller.roles;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.spinsoft.model.Role;
import in.spinsoft.service.RoleService;

@Controller
@RequestMapping("roles")
public class RoleController {
	
	@Autowired
	RoleService roleService;

	@GetMapping("/list")
	public String list(ModelMap modelMap, HttpSession session) throws Exception {

		try {

			List<Role> list = roleService.list();
			System.out.println(list);
			modelMap.addAttribute("ROLE_LIST", list);

			return "role/list";

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "/home";
		}
	}

	@GetMapping("/create")
	public String create() {
		return "role/add";
	}

	@GetMapping("/save")
	public String save(@RequestParam("code") String code, @RequestParam("name") String name,
			@RequestParam("level") Integer level, ModelMap modelMap, HttpSession session) throws Exception {
		try {
			Role role = new Role();
			role.setCode(code);
			role.setName(name);
			role.setLevel(level);
			roleService.insert(role);
			return "redirect:list";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "add";
		}
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("id") Long id, ModelMap modelMap) throws Exception {

		try {

			Role role = roleService.findById(id);
			modelMap.addAttribute("EDIT_ROLE", role);

			return "role/edit";

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "role/list";
		}

	}

	@GetMapping("/update")
	public String update(@PathVariable("id") Long roleId, @RequestParam("code") String code,
			@RequestParam("name") String name, @RequestParam("level") Integer level, ModelMap modelMap)
			throws Exception {
		try {

			Role role = roleService.findById(roleId);

			role.setCode(code);
			role.setName(name);
			role.setLevel(level);

			roleService.update(role);
			return "redirect:roles/list";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "edit";
		}
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Long id, ModelMap modelMap) throws Exception {

		try {
			roleService.delete(Long.valueOf(id));

			return "redirect:/roles/list";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "role/list";
		}

	}

}
