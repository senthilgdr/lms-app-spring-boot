package in.spinsoft.model;

import java.time.LocalDate;

import lombok.Data;

@Data

public class Role {

	private Long id;

	private String code;

	private String name;

	private Integer level;

	private boolean active;

	private LocalDate createdDate;

	private LocalDate modifiedDate;
}
