package in.spinsoft.model;

import lombok.Data;

@Data
public class EmployeeHierarchy {

	private Integer id;

	private Employee employee;

	private Employee manager;

}
