package in.spinsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.spinsoft.dao.EmployeeDAO;
import in.spinsoft.dao.UserMailManager;
import in.spinsoft.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	public Employee findByEmailId(String emailId, String password) {

		return employeeDAO.findByEmailIdAndPassword(emailId, password);
	}

	public Employee findById(Long empId) {

		return employeeDAO.findById(empId);
	}

	public List<Employee> findMyProfile(Long id) {

		return employeeDAO.findMyProfile(id);
	}

	public List<Employee> list() {

		return employeeDAO.list();
	}

	public void delete(Long empId) {

		employeeDAO.delete(empId);
	}

	public void update(Employee emp) {

		employeeDAO.update(emp);
	}

	public void register(Employee emp) {

		employeeDAO.registerEmployee(emp);
	}

	public void forgotPassword(String emailId) throws Exception {

		Employee employee = employeeDAO.findByEmailId(emailId);

		if (employee == null) {
			throw new Exception("MailId does not exists");
		}

		UserMailManager.sendPassword(employee);

	}

	public void changePassword(String emailId, String oldPassword, String newPassword) throws Exception {

		if (oldPassword.equals(newPassword)) {
			throw new Exception("Old Password and New Password is same");
		}

		Employee emp1 = employeeDAO.findByEmailIdAndPassword(emailId, oldPassword);

		if (emp1 == null) {
			throw new Exception("Invalid EmailId/Password");
		}

		boolean isModified = employeeDAO.changePassword(emailId, oldPassword, newPassword);
		if (isModified) {

			employeeDAO.addPasswordEntry(emp1.getId(), oldPassword, newPassword);
		} else {
			throw new Exception("Unable to change Password.");

		}

		UserMailManager.changePassword(emp1, newPassword);

	}
}
