package in.spinsoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.spinsoft.model.LeaveStatus;

@Repository
public class LeaveStatusDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public LeaveStatus findById(Long id) {

		String sql = "SELECT  ID, CODE, DESCRIPTION FROM LEAVE_STATUS WHERE ID= ?";
		LeaveStatus list = jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNo) -> {

			LeaveStatus lt = new LeaveStatus();
			lt.setId(rs.getLong("ID"));
			lt.setStatus(rs.getString("CODE"));
			lt.setDescription("DESCRIPTION");

			return lt;
		});
		return list;

	}
}
