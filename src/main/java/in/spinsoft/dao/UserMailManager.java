package in.spinsoft.dao;

import in.spinsoft.model.Employee;
import in.spinsoft.util.MailUtil;

/* 3 methods in mailmanager*/
public class UserMailManager {
	
	public static void sendNewRegistrationEmail(Employee emp) throws Exception {

		String subject = "Reg: Welcome to LMS App";

		StringBuilder message = new StringBuilder();

		message.append("Dear" + emp.getName() + ",\n");
		message.append("Successfully you are registered to LMS Website.");
		message.append("\n Please click the activation link below:");
		String activationLink ="http://localhost:8080/lmsapp-web/activateAccount.jsp?emailId=" + emp.getEmailId();
		message.append("\n" + activationLink + "\n");
		message.append("\n\nRegards ");
		message.append("\nLMS Support Team");

		MailUtil.sendMail(emp.getEmailId(), subject, message.toString());
		System.out.println("Mail sent!");

	}	
	
	public static void sendPassword(Employee emp) throws Exception {

		String subject = "Reg:Your Forgot Password";

		StringBuilder message = new StringBuilder();

		message.append("Dear " + emp.getName() + "...\n");
		message.append("Your password here " + emp.getPassword() + "\n"); 
																
		message.append("Regards \n");
		message.append("LMS Support Team");

		MailUtil.sendMail(emp.getEmailId(), subject, message.toString());

	}
	
	public static void changePassword(Employee emp, String newPassword) throws Exception {

		String subject = "Reg:Your New Password";
		StringBuilder message = new StringBuilder();

		message.append("Dear " + emp.getName() + "...\n");
		message.append("Your new password here (" + newPassword + ")\n"); // send password																			// password
		message.append("Regards \n");
		message.append("LMS Support Team");

		MailUtil.sendMail(emp.getEmailId(), subject, message.toString());
		
	}

}
