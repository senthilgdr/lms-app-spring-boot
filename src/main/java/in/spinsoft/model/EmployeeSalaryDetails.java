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

	private Long totalEarnings;

	public Long getTotalEarnings() {

		this.totalEarnings = basicPay + hra + conveyance + specialAllowance + medicalInsurance;

		return totalEarnings;
	}

	private Long totalDeductions;

	public Long getTotalDeductions() {
		this.totalDeductions = providentFund + incomeTax;

		return totalDeductions;
	}
}
