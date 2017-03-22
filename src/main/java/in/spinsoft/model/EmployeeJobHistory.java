package in.spinsoft.model;

import java.time.LocalDate;

import lombok.Data;

@Data

public class EmployeeJobHistory {

	private Long id;

	private Employee employee;

	private String designation;

	private String department;

	private LocalDate joiningDate;

	private LocalDate relievingDate;

}
