package in.spinsoft.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LeaveDetail {

	private Long id;

	private Employee employee;

	private LeaveType leaveType;

	private LocalDate fromDate;

	private LocalDate toDate;

	private Float noOfDays;

	private LocalDate appliedDate;

	private Employee modifiedBy;

	private LeaveStatus status;

	private LocalDate modifiedDate;

}
