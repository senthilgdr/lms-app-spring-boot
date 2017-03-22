package in.spinsoft.model;

import lombok.Data;

@Data

public class TaxDeclaration {

	private Long id;

	private Employee employee;

	private String panNo;

	private Integer houseRent;

	private String houseOwnerName;

	private Integer medicalBills;

	private Integer epfvpfContribution;

	private Integer ppf;

	private Integer scss;

	private Integer nsc;

	private Integer taxSavingFD;

	private Integer taxSavingBonds;

	private Integer taxSavingMutualFund;

	private Integer lifeInsurancePremiums;

	private Integer pensionPlan;

	private Integer centralGovtPlan;

	private Integer housingLoan;

	private Integer sukanyaSamriddhiAccount;

	private Integer stampDutyCharges;

	private Integer tuitionFees;

	private Integer additionalDeduction;

	private Integer deductionRgess;

	private Integer medicalInsuranceSelf;

	private Integer medicalInsuranceParents;

	private Integer educationLoan;

	private Integer medicalTreatment;

	private Integer expenditureMedicalTreatment;

	private Integer donationApprovedFunds;

	private Integer physicallyDisabled;

}
