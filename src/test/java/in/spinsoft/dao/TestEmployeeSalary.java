package in.spinsoft.dao;

import java.util.List;

import in.spinsoft.model.Employee;
import in.spinsoft.model.EmployeeSalaryDetails;

public class TestEmployeeSalary {

	public static void main(String[] args) {

		EmployeeSalaryDAO salaryDao = new EmployeeSalaryDAO();

		EmployeeSalaryDetails mySalaryDetails = salaryDao.listMySalary(1L);
		System.out.println(mySalaryDetails);

		List<EmployeeSalaryDetails> list = salaryDao.list();

		for (EmployeeSalaryDetails emp : list) {
			System.out.println(emp);
		}

		EmployeeSalaryDetails emp = new EmployeeSalaryDetails();

		Employee empolyee = new Employee();
		empolyee.setId(1l);

		//emp.setId(empolyee);
		emp.setBasicPay(200000L);
		emp.setHra(100000L);
		emp.setConveyance(1600L);
		emp.setSpecialAllowance(23600L);
		emp.setMedicalInsurance(1600L);
		emp.setProvidentFund(2000L);
		emp.setIncomeTax(12000L);

		// salaryDao.insert(emp);
		System.out.println("Insert");

	}
}
