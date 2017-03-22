package in.spinsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.spinsoft.dao.LeaveDetailDAO;
import in.spinsoft.model.LeaveDetail;

@Service
public class LeaveDetailService {
	@Autowired
	private LeaveDetailDAO ldDao;

	public void save(LeaveDetail ld) {
		ldDao.applyLeave(ld);
	}

	public List<LeaveDetail> list(Long employeeId) {

		return ldDao.list(employeeId);
	}

	public List<LeaveDetail> findAllPendingLeaves() {

		return ldDao.findAllPendingLeaves();
	}

	public void update(LeaveDetail ld) {
		ldDao.update(ld);
	}

	public LeaveDetail findById(Long leaveId) {

		return ldDao.findById(leaveId);
	}

	public List<LeaveDetail> findMyTeamPendingLeaves(long empId) {
		return ldDao.findMyTeamPendingLeaves(empId);
	}

}
