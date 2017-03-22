package in.spinsoft.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.spinsoft.model.Employee;
import in.spinsoft.model.TaxDeclaration;

@Repository
public class EmployeeTaxDeclarationDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void applyTaxDeclaration(TaxDeclaration td) {

		String sql = "INSERT INTO TAX_DECLARATION ( EMP_ID ,PAN_NUMBER, HOUSE_RENT, HOUSE_OWNER_NAME, MEDICAL_BILLS,EPF_VPF_CONTRIBUTION,"
				+ "PPF,SENIOR_CITITIZEN,NSC,TAX_SAVING_FD,TAX_SAVING_BONDS,ELSS,LIFE_INSURANCE,PENSION_PLAN,"
				+ "CENTRAL_GOVT_PENSION_PLAN,HOUSING_LOAN,SUKANYA_SAMRIDDHI_ACCOUNT,STAMP_DUTY_CHARGES,"
				+ "TUITION_FEES,ADDITIONAL_DEDUCTION,DEDUCTION_RGESS,MEDICAL_INSURANCE_SELF,"
				+ "MEDICAL_INSURANCE_PARENTS,EDUCATION_LOAN,MEDICAL_TREATMENT,EXPENDITURE_MEDICAL_TREATMENT,DONATION_APPROVED_FUNDS,"
				+ "PHYSICALLY_DISABLED_ASSESSE )"
				+ "VALUES ( ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?,?, ?, ?,? )";

		int rows = jdbcTemplate.update(sql, td.getEmployee().getId(), td.getPanNo(), td.getHouseRent(),
				td.getHouseOwnerName(), td.getMedicalBills(), td.getEpfvpfContribution(), td.getPpf(), td.getScss(),
				td.getNsc(), td.getTaxSavingFD(), td.getTaxSavingBonds(), td.getTaxSavingMutualFund(),
				td.getLifeInsurancePremiums(), td.getPensionPlan(), td.getCentralGovtPlan(), td.getHousingLoan(),
				td.getSukanyaSamriddhiAccount(), td.getStampDutyCharges(), td.getTuitionFees(),
				td.getAdditionalDeduction(), td.getDeductionRgess(), td.getMedicalInsuranceSelf(),
				td.getMedicalInsuranceParents(), td.getEducationLoan(), td.getMedicalTreatment(),
				td.getExpenditureMedicalTreatment(), td.getDonationApprovedFunds(), td.getPhysicallyDisabled());

		System.out.println("No of rows ApplyTax:" + rows);
	}

	public TaxDeclaration findByEmpId(Long empId) {

		String sql = "SELECT EMP_ID ,PAN_NUMBER, HOUSE_RENT, HOUSE_OWNER_NAME, MEDICAL_BILLS,EPF_VPF_CONTRIBUTION,"
				+ "PPF,SENIOR_CITITIZEN,NSC,TAX_SAVING_FD,TAX_SAVING_BONDS,ELSS,LIFE_INSURANCE,PENSION_PLAN,"
				+ "CENTRAL_GOVT_PENSION_PLAN,HOUSING_LOAN,SUKANYA_SAMRIDDHI_ACCOUNT,STAMP_DUTY_CHARGES,"
				+ "TUITION_FEES,ADDITIONAL_DEDUCTION,DEDUCTION_RGESS,MEDICAL_INSURANCE_SELF,"
				+ "MEDICAL_INSURANCE_PARENTS,EDUCATION_LOAN,MEDICAL_TREATMENT,EXPENDITURE_MEDICAL_TREATMENT,DONATION_APPROVED_FUNDS,"
				+ "PHYSICALLY_DISABLED_ASSESSE FROM TAX_DECLARATION WHERE EMP_ID = ? ";

		TaxDeclaration tax = jdbcTemplate.queryForObject(sql, new Object[] { empId }, (rs, rowno) -> {

			TaxDeclaration td = convert(rs);
			return td;
		});

		return tax;

	}

	public List<TaxDeclaration> findAll() {

		String sql = "SELECT EMP_ID ,PAN_NUMBER, HOUSE_RENT, HOUSE_OWNER_NAME, MEDICAL_BILLS,EPF_VPF_CONTRIBUTION,"
				+ "PPF,SENIOR_CITITIZEN,NSC,TAX_SAVING_FD,TAX_SAVING_BONDS,ELSS,LIFE_INSURANCE,PENSION_PLAN,"
				+ "CENTRAL_GOVT_PENSION_PLAN,HOUSING_LOAN,SUKANYA_SAMRIDDHI_ACCOUNT,STAMP_DUTY_CHARGES,"
				+ "TUITION_FEES,ADDITIONAL_DEDUCTION,DEDUCTION_RGESS,MEDICAL_INSURANCE_SELF,"
				+ "MEDICAL_INSURANCE_PARENTS,EDUCATION_LOAN,MEDICAL_TREATMENT,EXPENDITURE_MEDICAL_TREATMENT,DONATION_APPROVED_FUNDS,"
				+ "PHYSICALLY_DISABLED_ASSESSE FROM TAX_DECLARATION";

		List<TaxDeclaration> tax = jdbcTemplate.query(sql, new Object[] {}, (rs, rowno) -> {

			TaxDeclaration td = convert(rs);
			return td;
		});

		return tax;

	}

	private TaxDeclaration convert(ResultSet rs) throws SQLException {
		TaxDeclaration td = new TaxDeclaration();

		Employee emp = new Employee();
		emp.setId(rs.getLong("EMP_ID"));
		td.setEmployee(emp);
		td.setPanNo(rs.getString("PAN_NUMBER"));

		td.setHouseRent(rs.getInt("HOUSE_RENT"));
		td.setHouseOwnerName(rs.getString("HOUSE_OWNER_NAME"));
		td.setMedicalBills(rs.getInt("MEDICAL_BILLS"));
		td.setEpfvpfContribution(rs.getInt("EPF_VPF_CONTRIBUTION"));
		td.setPpf(rs.getInt("PPF"));
		td.setScss(rs.getInt("SENIOR_CITITIZEN"));
		td.setNsc(rs.getInt("NSC"));
		td.setTaxSavingFD(rs.getInt("TAX_SAVING_FD"));
		td.setTaxSavingBonds(rs.getInt("TAX_SAVING_BONDS"));
		td.setTaxSavingMutualFund(rs.getInt("ELSS"));
		td.setLifeInsurancePremiums(rs.getInt("LIFE_INSURANCE"));
		td.setPensionPlan(rs.getInt("PENSION_PLAN"));
		td.setCentralGovtPlan(rs.getInt("CENTRAL_GOVT_PENSION_PLAN"));
		td.setHousingLoan(rs.getInt("HOUSING_LOAN"));
		td.setSukanyaSamriddhiAccount(rs.getInt("SUKANYA_SAMRIDDHI_ACCOUNT"));
		td.setStampDutyCharges(rs.getInt("STAMP_DUTY_CHARGES"));
		td.setTuitionFees(rs.getInt("TUITION_FEES"));
		td.setAdditionalDeduction(rs.getInt("ADDITIONAL_DEDUCTION"));
		td.setDeductionRgess(rs.getInt("DEDUCTION_RGESS"));
		td.setMedicalInsuranceSelf(rs.getInt("MEDICAL_INSURANCE_SELF"));
		td.setMedicalInsuranceParents(rs.getInt("MEDICAL_INSURANCE_PARENTS"));
		td.setEducationLoan(rs.getInt("EDUCATION_LOAN"));
		td.setMedicalTreatment(rs.getInt("MEDICAL_TREATMENT"));
		td.setExpenditureMedicalTreatment(rs.getInt("EXPENDITURE_MEDICAL_TREATMENT"));
		td.setDonationApprovedFunds(rs.getInt("DONATION_APPROVED_FUNDS"));
		td.setPhysicallyDisabled(rs.getInt("PHYSICALLY_DISABLED_ASSESSE"));
		return td;
	}

}
