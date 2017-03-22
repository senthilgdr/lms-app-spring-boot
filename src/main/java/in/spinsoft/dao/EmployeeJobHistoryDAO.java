package in.spinsoft.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.spinsoft.model.Employee;
import in.spinsoft.model.EmployeeJobHistory;

@Repository
public class EmployeeJobHistoryDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<EmployeeJobHistory> list() {

		String sql = "SELECT e.ID,e.EMP_ID, emp.NAME as EMP_NAME, e.DESIGNATION,e.DEPARTMENT,e.DATE_OF_JOINING, e.END_OF_RELEVING"
				+ " FROM EMPLOYEE_JOB_HISTORY e, EMPLOYEES emp where e.EMP_ID = emp.ID ";

		List<EmployeeJobHistory> employee = jdbcTemplate.query(sql, new Object[] {}, (rs, rowNum) -> {

			return convert(rs);

		});
		return employee;

	}

	public EmployeeJobHistory listMyJob(Long empId) {
		EmployeeJobHistory employee = null;
		try {

			String sql = "SELECT e.ID,e.EMP_ID, emp.NAME as EMP_NAME, e.DESIGNATION,e.DEPARTMENT,e.DATE_OF_JOINING, e.END_OF_RELEVING"
					+ " FROM EMPLOYEE_JOB_HISTORY e, EMPLOYEES emp where e.EMP_ID = emp.ID and e.EMP_ID=?";

			employee = jdbcTemplate.queryForObject(sql, new Object[] { empId }, (rs, rowNum) -> {

				return convert(rs);

			});
		} catch (EmptyResultDataAccessException e) {

		}
		return employee;

	}

	private EmployeeJobHistory convert(ResultSet rs) throws SQLException {
		EmployeeJobHistory emp = new EmployeeJobHistory();
		emp.setId(rs.getLong("ID"));

		Employee employee = new Employee();
		employee.setId(rs.getLong("EMP_ID"));
		employee.setName(rs.getString("EMP_NAME"));
		emp.setEmployee(employee);
		emp.setDesignation(rs.getString("DESIGNATION"));
		emp.setDepartment(rs.getString("DEPARTMENT"));
		emp.setJoiningDate(rs.getDate("DATE_OF_JOINING")
				.toLocalDate()); /* DOJ is not null column */
		Date relievingDate = rs.getDate("END_OF_RELEVING");
		if (relievingDate != null) { // Relieving Date column may be null, to
										// avoid Null Pointer Exception we need
										// to check null
			emp.setRelievingDate(relievingDate.toLocalDate());
		}

		return emp;
	}

	public void insert(EmployeeJobHistory emp) {

		String sql = "INSERT INTO EMPLOYEE_JOB_HISTORY ( EMP_ID,DESIGNATION,DEPARTMENT,DATE_OF_JOINING)"
				+ "VALUES ( ?, ?,?,?)";

		int rows = jdbcTemplate.update(sql, emp.getEmployee().getId(), emp.getDesignation(), emp.getDepartment(),
				emp.getJoiningDate());

		System.out.println("No of rows Inserted:" + rows);
	}

	public void update(EmployeeJobHistory emp) {
		String sql = "UPDATE EMPLOYEE_JOB_HISTORY SET DESIGNATION=?,DEPARTMENT=?,DATE_OF_JOINING=? WHERE ID=?";

		int rows = jdbcTemplate.update(sql, emp.getDesignation(), emp.getDepartment(), emp.getJoiningDate(),
				emp.getId());

		System.out.println("No of rows Updated:" + rows);

	}

}
