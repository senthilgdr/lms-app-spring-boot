package in.spinsoft.dao;

import in.spinsoft.model.LeaveStatus;
import in.spinsoft.service.LeaveStatusService;

public class TestLeaveStatus {

	public static void main(String[] args) {
		

		LeaveStatusService ltService = new LeaveStatusService();
			
		LeaveStatus findById = ltService.findById(1L);
		System.out.println(findById);
	}
}
