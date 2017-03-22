package in.spinsoft.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.spinsoft.model.Employee;
import in.spinsoft.model.EmployeeHierarchy;


@Repository
public class EmployeeHierarchyDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	String empMgrNameQuery = "(SELECT NAME FROM EMPLOYEES WHERE ID = e.EMP_ID ) EMP_NAME ,"
			+ "(SELECT NAME FROM EMPLOYEES WHERE ID = e.MGR_ID ) AS MANAGER_NAME";

	public List<EmployeeHierarchy> list() {

		String sql = "SELECT e.ID,e.EMP_ID, e.MGR_ID ," + empMgrNameQuery + " FROM EMPLOYEE_HIERARCHY e";
		System.out.println(sql);
		List<EmployeeHierarchy> employee = jdbcTemplate.query(sql, new Object[] {}, (rs, rowNum) -> {

			return convert(rs);

		});
		return employee;

	}

	public List<EmployeeHierarchy> listMyTeam(Long empId) {

		String sql = "SELECT e.ID,e.EMP_ID, e.MGR_ID ," + empMgrNameQuery
				+ " FROM EMPLOYEE_HIERARCHY e where e.MGR_ID = ?";
		System.out.println(sql);
		List<EmployeeHierarchy> employee = jdbcTemplate.query(sql, new Object[] { empId }, (rs, rowNum) -> {

			return convert(rs);

		});
		return employee;

	}

	private EmployeeHierarchy convert(ResultSet rs) throws SQLException {
		EmployeeHierarchy emp = new EmployeeHierarchy();
		emp.setId(rs.getInt("ID"));

		Employee employee = new Employee();
		employee.setId(rs.getLong("EMP_ID"));
		employee.setName(rs.getString("EMP_NAME"));
		emp.setEmployee(employee);

		Employee employee1 = new Employee();
		employee1.setId(rs.getLong("MGR_ID"));
		employee1.setName(rs.getString("MANAGER_NAME"));
		emp.setEmployee(employee1);

		return emp;
	}

	public void insertEmployeeHierarchy(EmployeeHierarchy emp) {

		String sql = "INSERT INTO EMPLOYEE_HIERARCHY ( EMP_ID , MGR_ID)" + "VALUES ( ?, ?)";

		int rows = jdbcTemplate.update(sql, emp.getEmployee().getId(), emp.getManager().getId());

		System.out.println("No of rows Inserted:" + rows);
	}

	public void updateEmployeeHierarchy(EmployeeHierarchy emp) {

		String sql = "UPDATE EMPLOYEE_HIERARCHY SET EMP_ID=?,MGR_ID=? WHERE ID = ?";

		int rows = jdbcTemplate.update(sql, emp.getEmployee().getId(), emp.getManager().getId(), emp.getId());

		System.out.println("No of rows updated:" + rows);

	}

	public void delete(Long empId) {

		String sql = "DELETE FROM EMPLOYEE_HIERARCHY WHERE ID= ? ";
		int rows = jdbcTemplate.update(sql, empId);
		System.out.println("No of rows deleted:" + rows);

	}

	public EmployeeHierarchy findById(Long id) {

		System.out.println(id);
		String sql = "SELECT ID,EMP_ID, MGR_ID , " + empMgrNameQuery + " FROM EMPLOYEE_HIERARCHY  WHERE ID=?";
		System.out.println(sql);
		EmployeeHierarchy employee = jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNum) -> {

			return convert(rs);

		});
		return employee;

	}
}
