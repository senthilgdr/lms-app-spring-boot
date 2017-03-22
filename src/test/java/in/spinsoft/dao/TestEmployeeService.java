package in.spinsoft.dao;

import in.spinsoft.model.Employee;
import in.spinsoft.service.EmployeeService;

public class TestEmployeeService {

	public static void main(String[] args) throws Exception {

		EmployeeService empService = new EmployeeService();

		Employee emp = new Employee();
		/*
		 * emp.setEmailId("senthil.gdr@gmail.com");
		 * empService.changePassword(emp, "pass12", "pass123");
		 */

		empService.changePassword("senthil.gdr@gmail.com", "pass123", "pass1234");
		System.out.println("ChangedPassword");

		empService.forgotPassword("senthil.gdr@gmail.com");

	}
}
