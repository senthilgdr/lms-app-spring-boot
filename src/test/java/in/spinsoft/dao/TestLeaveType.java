package in.spinsoft.dao;

import in.spinsoft.model.LeaveType;
import in.spinsoft.service.LeaveTypeService;

public class TestLeaveType {

	public static void main(String[] args) {

		LeaveTypeService ltService = new LeaveTypeService();

		LeaveType findById = ltService.findById(1L);
		System.out.println(findById);
	}

}
