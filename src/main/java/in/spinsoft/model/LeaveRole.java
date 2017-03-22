package in.spinsoft.model;

import java.time.LocalDate;

import lombok.Data;

@Data

public class LeaveRole {

	private Long id;

	private Role role;

	private Long casual_leave;

	private Long sick_leave;

	private Long paid_leave;

	private Long maternity_leave;

	private Long paternity_leave;

	private Long privileged_leave;

	private boolean active;

	private LocalDate createdDate;

	private LocalDate modifiedDate;

}
