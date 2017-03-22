package in.spinsoft.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import in.spinsoft.model.Employee;
import in.spinsoft.model.LeaveDetail;
import in.spinsoft.model.LeaveStatus;
import in.spinsoft.model.LeaveType;

@Repository
public class LeaveDetailDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void applyLeave(LeaveDetail ld) {

		String sql = "INSERT INTO EMPLOYEE_LEAVE_DETAILS ( EMP_ID , FROM_DATE, TO_DATE, NO_OF_DAYS, LEAVE_TYPE,  STATUS_ID, APPLIED_DATE,MODIFIED_BY, MODIFIED_DATE )"
				+ "VALUES ( ?, ?, ?, ?, ?, ?, NOW(),?, NOW() )";

		int rows = jdbcTemplate.update(sql, ld.getEmployee().getId(), ld.getFromDate(), ld.getToDate(),
				ld.getNoOfDays(), ld.getLeaveType().getId(), ld.getStatus().getId(), ld.getEmployee().getId());

		System.out.println("No of rows inserted:" + rows);
	}

	public List<LeaveDetail> list(Long empId) {

		String sql = "SELECT e.NAME, ld.ID, ld.EMP_ID, FROM_DATE,TO_DATE, NO_OF_DAYS, LEAVE_TYPE AS LEAVE_TYPE_ID, STATUS_ID, ld.APPLIED_DATE, ld.MODIFIED_BY, ld.MODIFIED_DATE FROM EMPLOYEE_LEAVE_DETAILS ld , EMPLOYEES e WHERE ld.EMP_ID = e.ID AND EMP_ID= ?";

		List<LeaveDetail> list = jdbcTemplate.query(sql, new Object[] { empId }, (rs, rowNo) -> {

			long employeeId = rs.getLong("EMP_ID");
			Employee emp = new EmployeeDAO().findById(employeeId);

			long modifiedByUser = rs.getLong("MODIFIED_BY");
			Employee modifiedBy = new EmployeeDAO().findById(modifiedByUser);

			long statusId = rs.getLong("STATUS_ID");
			LeaveStatus ls = new LeaveStatusDAO().findById(statusId);

			long leaveTypeId = rs.getLong("LEAVE_TYPE_ID");
			LeaveType lt = new LeaveTypeDAO().findById(leaveTypeId);

			LeaveDetail ld = new LeaveDetail();
			ld.setId(rs.getLong("ID"));
			ld.setEmployee(emp);
			ld.setFromDate(rs.getDate("FROM_DATE").toLocalDate());
			ld.setToDate(rs.getDate("TO_DATE").toLocalDate());
			ld.setNoOfDays(rs.getFloat("NO_OF_DAYS"));
			ld.setLeaveType(lt);
			ld.setStatus(ls);
			ld.setAppliedDate(rs.getDate("APPLIED_DATE").toLocalDate());
			ld.setModifiedBy(modifiedBy);
			ld.setModifiedDate(rs.getDate("MODIFIED_DATE").toLocalDate());
			return ld;
		});
		return list;

	}

	public List<LeaveDetail> findAllPendingLeaves() {

		String sql = "SELECT e.NAME, ld.ID, ld.EMP_ID, FROM_DATE,TO_DATE, NO_OF_DAYS, LEAVE_TYPE AS LEAVE_TYPE_ID, STATUS_ID, ld.APPLIED_DATE, ld.MODIFIED_BY, ld.MODIFIED_DATE FROM EMPLOYEE_LEAVE_DETAILS ld , EMPLOYEES e WHERE ld.EMP_ID = e.ID AND STATUS_ID= 1";

		List<LeaveDetail> list = jdbcTemplate.query(sql, new Object[] {}, (rs, rowNo) -> {

			long employeeId = rs.getLong("EMP_ID");
			Employee emp = new EmployeeDAO().findById(employeeId);

			long modifiedByUser = rs.getLong("MODIFIED_BY");
			Employee modifiedBy = new EmployeeDAO().findById(modifiedByUser);

			long statusId = rs.getLong("STATUS_ID");
			LeaveStatus ls = new LeaveStatusDAO().findById(statusId);

			long leaveTypeId = rs.getLong("LEAVE_TYPE_ID");
			LeaveType lt = new LeaveTypeDAO().findById(leaveTypeId);

			LeaveDetail ld = new LeaveDetail();
			ld.setId(rs.getLong("ID"));
			ld.setEmployee(emp);
			ld.setFromDate(rs.getDate("FROM_DATE").toLocalDate());
			ld.setToDate(rs.getDate("TO_DATE").toLocalDate());
			ld.setNoOfDays(rs.getFloat("NO_OF_DAYS"));
			ld.setLeaveType(lt);
			ld.setStatus(ls);
			ld.setAppliedDate(rs.getDate("APPLIED_DATE").toLocalDate());
			ld.setModifiedBy(modifiedBy);
			ld.setModifiedDate(rs.getDate("MODIFIED_DATE").toLocalDate());
			return ld;
		});
		return list;

	}

	public List<LeaveDetail> findMyTeamPendingLeaves(long empId) {

		String sql = "SELECT e.NAME, ld.ID, ld.EMP_ID, FROM_DATE,TO_DATE, NO_OF_DAYS, LEAVE_TYPE AS LEAVE_TYPE_ID, STATUS_ID, ld.APPLIED_DATE, ld.MODIFIED_BY, ld.MODIFIED_DATE FROM EMPLOYEE_LEAVE_DETAILS ld , EMPLOYEES e WHERE ld.EMP_ID = e.ID AND STATUS_ID= 1 AND "
				+ "ld.EMP_ID in (SELECT emp_id FROM employee_hierarchy WHERE mgr_id=?)";

		List<LeaveDetail> list = jdbcTemplate.query(sql, new Object[] { empId }, (rs, rowNo) -> {

			long employeeId = rs.getLong("EMP_ID");
			Employee emp = new EmployeeDAO().findById(employeeId);

			long modifiedByUser = rs.getLong("MODIFIED_BY");
			Employee modifiedBy = new EmployeeDAO().findById(modifiedByUser);

			long statusId = rs.getLong("STATUS_ID");
			LeaveStatus ls = new LeaveStatusDAO().findById(statusId);

			long leaveTypeId = rs.getLong("LEAVE_TYPE_ID");
			LeaveType lt = new LeaveTypeDAO().findById(leaveTypeId);

			LeaveDetail ld = new LeaveDetail();
			ld.setId(rs.getLong("ID"));
			ld.setEmployee(emp);
			ld.setFromDate(rs.getDate("FROM_DATE").toLocalDate());
			ld.setToDate(rs.getDate("TO_DATE").toLocalDate());
			ld.setNoOfDays(rs.getFloat("NO_OF_DAYS"));
			ld.setLeaveType(lt);
			ld.setStatus(ls);
			ld.setAppliedDate(rs.getDate("APPLIED_DATE").toLocalDate());
			ld.setModifiedBy(modifiedBy);
			ld.setModifiedDate(rs.getDate("MODIFIED_DATE").toLocalDate());
			return ld;
		});
		return list;

	}

	public LeaveDetail findById(Long id) {

		String sql = "SELECT ld.ID, ld.EMP_ID, FROM_DATE,TO_DATE, NO_OF_DAYS, LEAVE_TYPE AS LEAVE_TYPE_ID, STATUS_ID, ld.APPLIED_DATE, ld.MODIFIED_BY, ld.MODIFIED_DATE FROM EMPLOYEE_LEAVE_DETAILS ld where ld.ID = ? ";

		LeaveDetail list = jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNo) -> {

			long employeeId = rs.getLong("EMP_ID");
			Employee emp = new EmployeeDAO().findById(employeeId);

			long modifiedByUser = rs.getLong("MODIFIED_BY");
			Employee modifiedBy = new EmployeeDAO().findById(modifiedByUser);

			long statusId = rs.getLong("STATUS_ID");
			LeaveStatus ls = new LeaveStatusDAO().findById(statusId);

			long leaveTypeId = rs.getLong("LEAVE_TYPE_ID");
			LeaveType lt = new LeaveTypeDAO().findById(leaveTypeId);

			LeaveDetail ld = new LeaveDetail();
			ld.setId(rs.getLong("ID"));
			ld.setEmployee(emp);
			ld.setFromDate(rs.getDate("FROM_DATE").toLocalDate());
			ld.setToDate(rs.getDate("TO_DATE").toLocalDate());
			ld.setNoOfDays(rs.getFloat("NO_OF_DAYS"));
			ld.setLeaveType(lt);
			ld.setStatus(ls);
			ld.setAppliedDate(rs.getDate("APPLIED_DATE").toLocalDate());
			ld.setModifiedBy(modifiedBy);
			ld.setModifiedDate(rs.getDate("MODIFIED_DATE").toLocalDate());
			return ld;
		});
		return list;

	}

	public void update(LeaveDetail ld) {

		String sql = "UPDATE EMPLOYEE_LEAVE_DETAILS SET STATUS_"
				+ "ID = ? , MODIFIED_BY = ? , MODIFIED_DATE= NOW() WHERE ID = ?";

		int rows = jdbcTemplate.update(sql, ld.getStatus().getId(), ld.getModifiedBy().getId(), ld.getId());

		System.out.println("No of rows updated:" + rows);

	}

	public void updateLeaveDetail(LeaveDetail ld) {

		String sql = "UPDATE EMPLOYEE_LEAVE_DETAILS SET FROM_DATE=?,TO_DATE=?,"
				+ "NO_OF_DAYS = ? ,LEAVE_TYPE = ?  WHERE ID = ?";
		System.out.println(sql);
		System.out.println(ld.getFromDate() + "-" + ld.getToDate() + "-" + ld.getNoOfDays() + "-"
				+ ld.getLeaveType().getId() + "-" + ld.getId());
		int rows = jdbcTemplate.update(sql, ld.getFromDate(), ld.getToDate(), ld.getNoOfDays(),
				ld.getLeaveType().getId(), ld.getId());

		System.out.println("No of rows updated:" + rows);

	}

	public void delete(Long leaveId) {

		String sql = "DELETE FROM EMPLOYEE_LEAVE_DETAILS WHERE ID= ? ";
		int rows = jdbcTemplate.update(sql, leaveId);
		System.out.println("No of rows deleted:" + rows);

	}

	private class LeaveDetailRowMapper implements RowMapper<LeaveDetail> {

		public LeaveDetail mapRow(ResultSet rs, int rowNo) throws SQLException {

			Employee emp = new Employee();
			emp.setId(rs.getLong("EMP_ID"));

			Employee modifiedBy = new Employee();
			modifiedBy.setId(rs.getLong("MODIFIED_BY"));

			LeaveStatus ls = new LeaveStatus();
			ls.setId(rs.getLong("STATUS_ID"));

			LeaveType lt = new LeaveType();
			lt.setId(rs.getLong("LEAVE_TYPE"));

			LeaveDetail ld = new LeaveDetail();
			ld.setId(rs.getLong("ID"));
			ld.setEmployee(emp);
			ld.setFromDate(rs.getDate("FROM_DATE").toLocalDate());
			ld.setToDate(rs.getDate("TO_DATE").toLocalDate());
			ld.setNoOfDays(rs.getFloat("NO_OF_DAYS"));
			ld.setLeaveType(lt);
			ld.setStatus(ls);
			ld.setModifiedBy(modifiedBy);
			ld.setModifiedDate(rs.getDate("MODIFIED_DATE").toLocalDate());
			return ld;
		}

	}
}
