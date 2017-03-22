package in.spinsoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.spinsoft.model.LeaveType;

@Repository
public class LeaveTypeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public LeaveType findById(Long id) {

		String sql = "SELECT ID, LEAVE_TYPE, ACTIVE FROM LEAVE_TYPES WHERE ID= ?";
		LeaveType list = jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNo) -> {

			LeaveType lt = new LeaveType();
			lt.setId(rs.getLong("ID"));
			lt.setType(rs.getString("LEAVE_TYPE"));
			lt.setActive(rs.getBoolean("ACTIVE"));
			return lt;
		});
		return list;

	}
}
