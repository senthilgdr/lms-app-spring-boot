package in.spinsoft.controller.employee;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.spinsoft.model.Employee;
import in.spinsoft.model.TaxDeclaration;
import in.spinsoft.service.EmployeeTaxService;

@Controller
@RequestMapping("employeesTax")
public class EmployeeTaxDeclarationController {
	@Autowired
	EmployeeTaxService employeeTaxService;

	@GetMapping("/create")
	public String create() {
		return "employeetax/add";
	}

	@GetMapping("/save")
	public String save(@RequestParam("name") String empName, @RequestParam("pan") String panNo,
			@RequestParam("houseRent") Integer houseRent, @RequestParam("houseOwnerName") String houseOwnerName,
			@RequestParam("medicalBills") Integer medicalBills,
			@RequestParam("epfContribution") Integer epfContribution, @RequestParam("ppf") Integer ppf,
			@RequestParam("scss") Integer scss, @RequestParam("nsc") Integer nsc,
			@RequestParam("taxSavingFixedDeposit") Integer taxSavingFixedDeposit,
			@RequestParam("taxSavingBonds") Integer taxSavingBonds,
			@RequestParam("taxSavingMutualFund") Integer taxSavingMutualFund,
			@RequestParam("lifeInsurancePremiums") Integer lifeInsurancePremiums,
			@RequestParam("pensionPlan") Integer pensionPlan, @RequestParam("centralGovtPlan") Integer centralGovtPlan,
			@RequestParam("housingLoan") Integer housingLoan,
			@RequestParam("sukanyaSamriddhiAccount") Integer sukanyaSamriddhiAccount,
			@RequestParam("stampDutyCharges") Integer stampDutyCharges,
			@RequestParam("tuitionFees") Integer tuitionFees,
			@RequestParam("additionalDeduction") Integer additionalDeduction,
			@RequestParam("deductionRgess") Integer deductionRgess,
			@RequestParam("medicalInsuranceSelf") Integer medicalInsuranceSelf,
			@RequestParam("medicalInsuranceParents") Integer medicalInsuranceParents,
			@RequestParam("educationLoan") Integer educationLoan,
			@RequestParam("medicalTreatment") Integer medicalTreatment,
			@RequestParam("expenditureMedicalTreatment") Integer expenditureMedicalTreatment,
			@RequestParam("donationApprovedFunds") Integer donationApprovedFunds,
			@RequestParam("physicallyDisabled") Integer physicallyDisabled,

			ModelMap modelMap, HttpSession session) throws Exception {

		try {
			TaxDeclaration td = new TaxDeclaration();

			
			Employee sessionEmp = (Employee) session.getAttribute("LOGGED_IN_USER");
			Employee emp = new Employee();
			emp.setId(sessionEmp.getId());

			td.setEmployee(emp);
			td.setPanNo(panNo);
			td.setHouseRent((houseRent));
			td.setHouseOwnerName(houseOwnerName);
			td.setMedicalBills((medicalBills));
			td.setEpfvpfContribution((epfContribution));
			td.setPpf((ppf));
			td.setScss((scss));
			td.setNsc((nsc));
			td.setTaxSavingFD((taxSavingFixedDeposit));
			td.setTaxSavingBonds((taxSavingBonds));
			td.setTaxSavingMutualFund((taxSavingMutualFund));
			td.setLifeInsurancePremiums((lifeInsurancePremiums));
			td.setPensionPlan((pensionPlan));
			td.setCentralGovtPlan((centralGovtPlan));
			td.setHousingLoan((housingLoan));
			td.setSukanyaSamriddhiAccount((sukanyaSamriddhiAccount));
			td.setStampDutyCharges((stampDutyCharges));
			td.setTuitionFees((tuitionFees));
			td.setAdditionalDeduction((additionalDeduction));
			td.setDeductionRgess((deductionRgess));
			td.setMedicalInsuranceSelf((medicalInsuranceSelf));
			td.setMedicalInsuranceParents((medicalInsuranceParents));
			td.setEducationLoan((educationLoan));
			td.setMedicalTreatment((medicalTreatment));
			td.setExpenditureMedicalTreatment((expenditureMedicalTreatment));
			td.setDonationApprovedFunds((donationApprovedFunds));
			td.setPhysicallyDisabled((physicallyDisabled));

			System.out.println("Add:" + td);

			employeeTaxService.register(td);
			System.out.println("ADDED" + td);
			return "redirect:list";
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "add";
		}

	}

	@GetMapping("/list")
	public String list(ModelMap modelMap, HttpSession session) throws Exception {

		try {
			Employee emp = (Employee) session.getAttribute("LOGGED_IN_USER");

			List<TaxDeclaration> list = employeeTaxService.findAll();
			System.out.println(list);
			modelMap.addAttribute("EMPLOYEE_TAX_LIST", list);

			return "employeetax/list";

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "/home";
		}
	}

	@GetMapping("/myTaxlist")
	public String myTaxlist(ModelMap modelMap, HttpSession session) throws Exception {

		try {
			Employee emp = (Employee) session.getAttribute("LOGGED_IN_USER");

			TaxDeclaration td = employeeTaxService.findByEmpId(emp.getId());
			System.out.println(td);
			modelMap.addAttribute("MY_TAX_LIST", td);

			return "employeetax/mytaxlist";

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.addAttribute("errorMessage", e.getMessage());
			return "/home";
		}
	}
}
