package in.spinsoft.dao;

import java.util.List;

import in.spinsoft.model.Role;
import in.spinsoft.service.RoleService;

public class TestRole {

	public static void main(String[] args) {		

		RoleService roleService = new RoleService();
			
		Role role= roleService.findById(1L);
		System.out.println(role);
		 
		List<Role> list=roleService.list();
		
		System.out.println(list);
		
		Role role1=new Role();
		role1.setCode("SPOO7");
		role1.setName("Trainee");
		role1.setLevel(5);
		roleService.insert(role1);
	}
}
