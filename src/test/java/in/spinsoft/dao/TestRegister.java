package in.spinsoft.dao;

import in.spinsoft.dao.EmployeeDAO;
import in.spinsoft.model.Employee;
import in.spinsoft.model.Role;

public class TestRegister {

	public static void main(String[] args) {
		Employee emp = new Employee();
		emp.setCode("SP0001");
		emp.setName("SenthilGanesh");
		emp.setEmailId("ragu@gmail.com");
		emp.setPassword("pa");
		emp.setMobileNo(9000L);

		Role r = new Role();
		r.setId(10L); // employeee
		emp.setRole(r);
		System.out.println(emp);

		EmployeeDAO dao = new EmployeeDAO();
		dao.registerEmployee(emp);
	}

}
