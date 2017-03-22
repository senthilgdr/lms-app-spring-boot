package in.spinsoft.dao;

import java.util.List;

import in.spinsoft.model.EmployeeHierarchy;

public class TestEmployeeHierarchy {

	public static void main(String[] args) {

		EmployeeHierarchyDAO empDao = new EmployeeHierarchyDAO();

		/*
		 * List<EmployeeHierarchy> list = empDao.list();
		 * 
		 * for(EmployeeHierarchy eh:list){ System.out.println(eh); }
		 */

		/*
		 * EmployeeHierarchy employeeHierarchy=new EmployeeHierarchy();
		 * employeeHierarchy.setEmpId(7); employeeHierarchy.setMgrId(6);
		 * 
		 * empDao.insertEmployeeHierarchy(employeeHierarchy);
		 * System.out.println(employeeHierarchy);
		 */

		/*
		 * EmployeeHierarchy employeeHierarchy=empDao.findById(5L);
		 * 
		 * employeeHierarchy.setEmpId(7); employeeHierarchy.setMgrId(4);
		 * 
		 * empDao.updateEmployeeHierarchy(employeeHierarchy);
		 * System.out.println(employeeHierarchy);
		 * 
		 * 
		 * empDao.delete(5L); System.out.println("Deleted:"+empDao);
		 */

		List<EmployeeHierarchy> list = empDao.listMyTeam(6L);

		for (EmployeeHierarchy eh : list) {
			System.out.println(eh);
		}
	}
}
