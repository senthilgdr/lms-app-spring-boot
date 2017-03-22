package in.spinsoft.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.spinsoft.model.Employee;
import in.spinsoft.model.EmployeeSalaryDetails;

@Repository
public class EmployeeSalaryDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String empNameQuery = "(SELECT NAME FROM EMPLOYEES emp where emp.ID = e.EMP_ID) EMP_NAME ";

	public List<EmployeeSalaryDetails> list() {

		String sql = "SELECT e.ID,e.EMP_ID, " + empNameQuery
				+ " , e.BASIC_PAY,e.HRA,e.CONVEYANCE, e.SPECIAL_ALLOWANCE,e.MEDICAL_INSURANCE,e.PROVIDENT_FUND,e.INCOME_TAX FROM EMPLOYEE_SALARY_DETAILS e";

		System.out.println(sql);
		List<EmployeeSalaryDetails> employee = jdbcTemplate.query(sql, new Object[] {}, (rs, rowNum) -> {

			return convert(rs);

		});
		return employee;

	}

	public EmployeeSalaryDetails listMySalary(Long empId) {

		String sql = "SELECT e.ID,e.EMP_ID," + empNameQuery
				+ ",e.BASIC_PAY,e.HRA,e.CONVEYANCE, e.SPECIAL_ALLOWANCE,e.MEDICAL_INSURANCE,e.PROVIDENT_FUND,e.INCOME_TAX"
				+ " FROM EMPLOYEE_SALARY_DETAILS e WHERE e.EMP_ID=?";
		System.out.println(sql);
		EmployeeSalaryDetails employee = jdbcTemplate.queryForObject(sql, new Object[] { empId }, (rs, rowNum) -> {

			return convert(rs);

		});
		return employee;

	}

	private EmployeeSalaryDetails convert(ResultSet rs) throws SQLException {
		EmployeeSalaryDetails emp = new EmployeeSalaryDetails();
		emp.setId(rs.getLong("ID"));

		Employee employee = new Employee();
		employee.setId(rs.getLong("EMP_ID"));
		employee.setName(rs.getString("EMP_NAME"));
		emp.setEmployee(employee);
		emp.setBasicPay(rs.getLong("BASIC_PAY"));
		emp.setHra(rs.getLong("HRA"));
		emp.setConveyance(rs.getLong("CONVEYANCE"));
		emp.setSpecialAllowance(rs.getLong("SPECIAL_ALLOWANCE"));
		emp.setMedicalInsurance(rs.getLong("MEDICAL_INSURANCE"));
		emp.setProvidentFund(rs.getLong("PROVIDENT_FUND"));
		emp.setIncomeTax(rs.getLong("INCOME_TAX"));

		return emp;
	}

	public void insert(EmployeeSalaryDetails emp) {

		String sql = "INSERT INTO EMPLOYEE_SALARY_DETAILS ( EMP_ID , BASIC_PAY,HRA,CONVEYANCE,SPECIAL_ALLOWANCE,MEDICAL_INSURANCE,PROVIDENT_FUND,INCOME_TAX)"
				+ "VALUES ( ?, ?,?, ?,?, ?,?,?)";

		int rows = jdbcTemplate.update(sql, emp.getEmployee().getId(), emp.getBasicPay(), emp.getHra(),
				emp.getConveyance(), emp.getSpecialAllowance(), emp.getMedicalInsurance(), emp.getProvidentFund(),
				emp.getIncomeTax());

		System.out.println("No of rows Inserted:" + rows);
	}

}
