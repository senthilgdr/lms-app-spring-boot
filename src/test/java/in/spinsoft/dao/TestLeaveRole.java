package in.spinsoft.dao;

import java.util.List;

import in.spinsoft.model.LeaveRole;
import in.spinsoft.service.LeaveRoleService;

public class TestLeaveRole {

	public static void main(String[] args) {		
		
		LeaveRoleService ldDao = new LeaveRoleService();
		
		List<LeaveRole> list = ldDao.list();
		for (LeaveRole LeaveRole : list) {
			System.out.println(LeaveRole);
		}
		
	}
}
