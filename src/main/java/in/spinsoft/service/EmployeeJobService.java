package in.spinsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.spinsoft.dao.EmployeeJobHistoryDAO;
import in.spinsoft.model.EmployeeJobHistory;

@Service
public class EmployeeJobService {
	@Autowired
	private EmployeeJobHistoryDAO employeeJobDAO;

	public List<EmployeeJobHistory> list() {

		return employeeJobDAO.list();
	}

	public EmployeeJobHistory listMyJob(Long empId) {

		return employeeJobDAO.listMyJob(empId);
	}

	public void insert(EmployeeJobHistory emp) {

		if (emp.getId() == null) {
			employeeJobDAO.insert(emp);
		} else {
			employeeJobDAO.update(emp);
		}
	}

}
