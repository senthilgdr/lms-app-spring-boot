package in.spinsoft.dao;

import java.util.List;

import in.spinsoft.model.Employee;
import in.spinsoft.model.TaxDeclaration;

public class TestTaxDecalration {

	public static void main(String[] args) {
		TaxDeclaration td = new TaxDeclaration();

		Employee emp = new Employee();
		emp.setId(1L);

		td.setEmployee(emp);
		td.setPanNo("ANYPG123L");
		td.setHouseRent(10000);
		td.setHouseOwnerName("RajaRathinam");
		td.setMedicalBills(10000);
		td.setEpfvpfContribution(10000);
		td.setPpf(10000);
		td.setScss(10000);
		td.setNsc(10000);
		td.setTaxSavingFD(10000);
		td.setTaxSavingBonds(10000);
		td.setTaxSavingMutualFund(10000);
		td.setLifeInsurancePremiums(10000);
		td.setPensionPlan(10000);
		td.setCentralGovtPlan(10000);
		td.setHousingLoan(10000);
		td.setSukanyaSamriddhiAccount(10000);
		td.setStampDutyCharges(10000);
		td.setTuitionFees(10000);
		td.setAdditionalDeduction(10000);
		td.setDeductionRgess(10000);
		td.setMedicalInsuranceSelf(10000);
		td.setMedicalInsuranceParents(10000);
		td.setEducationLoan(10000);
		td.setMedicalTreatment(10000);
		td.setExpenditureMedicalTreatment(10000);
		td.setDonationApprovedFunds(10000);
		td.setPhysicallyDisabled(10000);

		EmployeeTaxDeclarationDAO dao = new EmployeeTaxDeclarationDAO();
		// dao.applyTaxDeclaration(td);
		System.out.println("Insertd" + td);

		/*
		 * TaxDeclaration findByEmpId = dao.findByEmpId(1L);
		 * System.out.println(findByEmpId);
		 */

		List<TaxDeclaration> list = dao.findAll();
		for (TaxDeclaration taxDeclaration : list) {
			System.out.println(taxDeclaration);
		}
	}

}
