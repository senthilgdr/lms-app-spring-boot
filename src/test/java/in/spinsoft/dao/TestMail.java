package in.spinsoft.dao;

import in.spinsoft.util.MailUtil;

public class TestMail {

	public static void main(String[] args) throws Exception {

		String emailId = "gdr@gmail.com";
		String forgotPasswordLink = "http://localhost:8080/lmsapp-web/activateAccount.jsp?emailId=" + emailId;
		MailUtil.sendMail(emailId, "Welcome Mail", forgotPasswordLink);
	}
}
