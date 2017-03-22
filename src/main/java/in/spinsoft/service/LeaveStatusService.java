package in.spinsoft.service;

import in.spinsoft.model.LeaveStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.spinsoft.dao.LeaveStatusDAO;

@Service
public class LeaveStatusService {

	@Autowired
	private LeaveStatusDAO leaveStatusDAO ;
	
	public LeaveStatus findById(Long id){
		return leaveStatusDAO.findById(id);
	}
	
}
