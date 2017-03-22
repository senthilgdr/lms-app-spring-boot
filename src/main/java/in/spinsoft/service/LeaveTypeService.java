package in.spinsoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.spinsoft.dao.LeaveTypeDAO;
import in.spinsoft.model.LeaveType;

@Service
public class LeaveTypeService {

	@Autowired
	private LeaveTypeDAO leaveTypeDAO ;
	
	public LeaveType findById(Long id) {
		return leaveTypeDAO.findById(id);
	}

}
