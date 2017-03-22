package in.spinsoft.dao;

import java.text.ParseException;
import java.time.LocalDate;

import in.spinsoft.model.Employee;
import in.spinsoft.model.EmployeeJobHistory;



public class TestEmployeeJobHistory {

	public static void main(String[] args) throws ParseException {

		EmployeeJobHistoryDAO jobDao = new EmployeeJobHistoryDAO();

		/*
		 * EmployeeJobHistory myJobDetails = jobDao.listMyJob(1L);
		 * System.out.println(myJobDetails);
		 * 
		 * List<EmployeeJobHistory> list = jobDao.list();
		 * 
		 * for (EmployeeJobHistory emp : list) { System.out.println(emp); }
		 */

		EmployeeJobHistory emp = new EmployeeJobHistory();

		Employee empolyee = new Employee();
		empolyee.setId(3l);

		emp.setEmployee(empolyee);
		emp.setDesignation("HR");
		emp.setDepartment("Hr");

		LocalDate dateOfJoing = LocalDate.parse("2017-05-12");
		emp.setJoiningDate(dateOfJoing);

		jobDao.insert(emp);
		System.out.println("Insert");

	}
}
