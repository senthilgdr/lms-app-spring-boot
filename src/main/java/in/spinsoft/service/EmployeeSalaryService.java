package in.spinsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.spinsoft.dao.EmployeeSalaryDAO;
import in.spinsoft.model.EmployeeSalaryDetails;

@Service
public class EmployeeSalaryService {

	@Autowired
	private EmployeeSalaryDAO employeeSalaryDAO;

	public List<EmployeeSalaryDetails> list() {

		return employeeSalaryDAO.list();
	}

	public EmployeeSalaryDetails listMySalary(Long empId) {

		return employeeSalaryDAO.listMySalary(empId);
	}

	public void insert(EmployeeSalaryDetails emp) {

		employeeSalaryDAO.insert(emp);
	}

}
