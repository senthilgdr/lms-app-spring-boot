package in.spinsoft.model;

import lombok.Data;

@Data

public class EmployeeSalaryDetails {

	private Long id;

	private Employee employee;

	private Long basicPay;

	private Long hra;

	private Long conveyance;

	private Long specialAllowance;

	private Long medicalInsurance;

	private Long providentFund;

	private Long incomeTax;

	public Long getTotalEarnings() {
		return basicPay + hra + conveyance + specialAllowance + medicalInsurance;
	}

	public Long getTotalDeductions() {
		return providentFund + incomeTax;
	}
}
