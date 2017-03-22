package in.spinsoft.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.spinsoft.model.Role;

@Repository
public class RoleDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Role findById(Long id) {

		String sql = "SELECT  ID, ROLE_CODE, ROLE_NAME,LEVEL,CREATED_DATE,MODIFIED_DATE FROM ROLE WHERE ID= ?";
		Role list = jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNo) -> {

			Role role = new Role();
			role.setId(rs.getLong("ID"));
			role.setCode(rs.getString("ROLE_CODE"));
			role.setName(rs.getString("ROLE_NAME"));
			role.setLevel(rs.getInt("LEVEL"));
			role.setCreatedDate(rs.getDate("CREATED_DATE").toLocalDate());
			role.setModifiedDate(rs.getDate("MODIFIED_DATE").toLocalDate());
			return role;
		});
		return list;

	}

	private Role convert(ResultSet rs) throws SQLException {
		Role role = new Role();
		role.setId(rs.getLong("ID"));
		role.setCode(rs.getString("ROLE_CODE"));
		role.setName(rs.getString("ROLE_NAME"));
		role.setLevel(rs.getInt("LEVEL"));
		role.setCreatedDate(rs.getDate("CREATED_DATE").toLocalDate());
		role.setModifiedDate(rs.getDate("MODIFIED_DATE").toLocalDate());
		return role;
	}

	public List<Role> list() {

		String sql = "SELECT ID, ROLE_CODE, ROLE_NAME,LEVEL,CREATED_DATE,MODIFIED_DATE FROM ROLE";

		List<Role> list = jdbcTemplate.query(sql, new Object[] {}, (rs, rowNum) -> {
			return convert(rs);
		});
		return list;
	}

	public void insert(Role role) {

		String sql = "INSERT INTO ROLE ( ROLE_CODE , ROLE_NAME,LEVEL,CREATED_DATE,MODIFIED_DATE)"
				+ "VALUES ( ?, ?,?,NOW(),NOW())";

		int rows = jdbcTemplate.update(sql, role.getCode(), role.getName(), role.getLevel());

		System.out.println("No of rows Register:" + rows);
	}

	public void update(Role role) {

		String sql = "UPDATE ROLE SET ROLE_CODE=?,ROLE_NAME=?,LEVEL=? WHERE ID=? ";

		Integer rows = jdbcTemplate.update(sql, role.getCode(), role.getName(), role.getLevel(), role.getId());

		System.out.println("No of rows Changed:" + rows);

	}

	public void delete(Long roleId) {

		String sql = "DELETE FROM ROLE WHERE ID= ? ";
		int rows = jdbcTemplate.update(sql, roleId);
		System.out.println("No of rows deleted:" + rows);

	}
}
