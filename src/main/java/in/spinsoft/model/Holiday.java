package in.spinsoft.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Holiday {

	private Long id;

	private LocalDate holidayDate;

	private String status;

}
