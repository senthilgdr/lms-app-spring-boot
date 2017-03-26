package in.spinsoft.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.spinsoft.model.LeaveRole;
import in.spinsoft.model.Role;

@Repository
public class LeaveRoleDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RoleDAO roleDAO;

	public List<LeaveRole> list() {

		String sql = "SELECT rl.ID,rl.ROLE_ID,CASUAL_LEAVE,SICK_LEAVE,PAID_LEAVE,"
				+ "MATERNITY_LEAVE,PATERNITY_LEAVE,PRIVILEGED_LEAVE,rl.CREATED_DATE,rl.MODIFIED_DATE FROM ROLE_LEAVES rl,ROLE r WHERE rl.ROLE_ID=r.ID";

		List<LeaveRole> roleList = jdbcTemplate.query(sql, new Object[] {}, (rs, rowNo) -> {

			long roleId = rs.getLong("ROLE_ID");
			Role role = roleDAO.findById(roleId);

			LeaveRole roleLeave = new LeaveRole();
			roleLeave.setId(rs.getLong("ID"));
			roleLeave.setRole(role);
			roleLeave.setCasual_leave(rs.getLong("CASUAL_LEAVE"));
			roleLeave.setSick_leave(rs.getLong("SICK_LEAVE"));
			roleLeave.setPaid_leave(rs.getLong("PAID_LEAVE"));
			roleLeave.setMaternity_leave(rs.getLong("MATERNITY_LEAVE"));
			roleLeave.setPaternity_leave(rs.getLong("PATERNITY_LEAVE"));
			roleLeave.setPrivileged_leave(rs.getLong("PRIVILEGED_LEAVE"));
			roleLeave.setCreatedDate(rs.getDate("CREATED_DATE").toLocalDate());

			roleLeave.setModifiedDate(rs.getDate("MODIFIED_DATE").toLocalDate());
			return roleLeave;
		});
		return roleList;

	}

	public LeaveRole findById(Long id) {

		String sql = "SELECT rl.ID,rl.ROLE_ID,CASUAL_LEAVE,SICK_LEAVE,PAID_LEAVE,"
				+ "MATERNITY_LEAVE,PATERNITY_LEAVE,PRIVILEGED_LEAVE,rl.CREATED_DATE,rl.MODIFIED_DATE FROM ROLE_LEAVES rl,ROLE r WHERE rl.ROLE_ID=r.ID AND r.ID=?";

		LeaveRole list = jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNo) -> {

			long roleId = rs.getLong("ROLE_ID");
			Role role = roleDAO.findById(roleId);

			LeaveRole roleLeave = new LeaveRole();
			roleLeave.setId(rs.getLong("ID"));
			roleLeave.setRole(role);
			roleLeave.setCasual_leave(rs.getLong("CASUAL_LEAVE"));
			roleLeave.setSick_leave(rs.getLong("SICK_LEAVE"));
			roleLeave.setPaid_leave(rs.getLong("PAID_LEAVE"));
			roleLeave.setMaternity_leave(rs.getLong("MATERNITY_LEAVE"));
			roleLeave.setPaternity_leave(rs.getLong("PATERNITY_LEAVE"));
			roleLeave.setPrivileged_leave(rs.getLong("PRIVILEGED_LEAVE"));
			roleLeave.setCreatedDate(rs.getDate("CREATED_DATE").toLocalDate());

			roleLeave.setModifiedDate(rs.getDate("MODIFIED_DATE").toLocalDate());
			return roleLeave;
		});
		return list;

	}

}
