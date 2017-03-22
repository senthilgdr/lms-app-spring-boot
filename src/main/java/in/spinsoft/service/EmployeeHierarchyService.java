package in.spinsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.spinsoft.dao.EmployeeHierarchyDAO;
import in.spinsoft.model.EmployeeHierarchy;

@Service
public class EmployeeHierarchyService {

	@Autowired
	private EmployeeHierarchyDAO employeeHierarchyDAO;

	public List<EmployeeHierarchy> list() {

		return employeeHierarchyDAO.list();
	}

	public List<EmployeeHierarchy> listMyTeam(Long empId) {

		return employeeHierarchyDAO.listMyTeam(empId);
	}

	public void insert(EmployeeHierarchy emp) {

		employeeHierarchyDAO.insertEmployeeHierarchy(emp);
	}

	public void update(EmployeeHierarchy emp) {
		employeeHierarchyDAO.updateEmployeeHierarchy(emp);
	}

	public void delete(Long empId) {
		employeeHierarchyDAO.delete(empId);
	}

	public EmployeeHierarchy findById(Long id) {

		return employeeHierarchyDAO.findById(id);
	}
}
