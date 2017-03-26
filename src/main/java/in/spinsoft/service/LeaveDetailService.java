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

	public List<LeaveDetail> list() {

		return ldDao.list();
	}

	public List<LeaveDetail> findMyLeaves(Long employeeId) {

		return ldDao.findMyLeaves(employeeId);
	}

	public void update(LeaveDetail ld) {
		ldDao.update(ld);
	}
	public void updateLeaveDetail(LeaveDetail ld) {
		ldDao.updateLeaveDetail(ld);
	}
	public LeaveDetail findById(Long leaveId) {

		return ldDao.findById(leaveId);
	}

	public List<LeaveDetail> findMyTeam(long empId) {
		return ldDao.findMyTeam(empId);
	}

}
