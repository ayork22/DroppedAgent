import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

	public static void sendemail(String disconnectedAgents) {

		// TEST AREA ********
		// Address[] cc = new Address[] {InternetAddress.parse("abc@abc.com"),
		// InternetAddress.parse("abc@def.com"),
		// InternetAddress.parse("ghi@abc.com")};
		// message.addRecipients(Message.RecipientType.CC, cc);

		// Recipient's email ID needs to be mentioned.
		// String to = "alex.test@gmail.com";
		// String emailTo = GetProperties.getPropertyValue("Email_To");
		// Sender's email ID needs to be mentioned
		// String from = "IntrsoscopeMonitor@ssa.gov";
		String emailFrom = GetProperties.getPropertyValue("Email_From");
		// Assuming you are sending email from localhost
		// String host = "localhost";
		String smptServer = GetProperties.getPropertyValue("SMTP_Server");
		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", smptServer);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(emailFrom));

			// Set To: header field of the header.
			// Works for one Address
			// message.addRecipient(Message.RecipientType.TO, new
			// InternetAddress(emailTo));
			// MultipleAddresses
			message.addRecipients(Message.RecipientType.TO,
					InternetAddress.parse(GetProperties.getPropertyValue("Email_To")));

			// Set Subject: header field
			message.setSubject("Dropped Agent Notificaiton");

			// Send the actual HTML message, as big as you like
			//Working Example
			message.setContent("<h1>The Below Agents are Not Connected to APM</h1>" + "\n" + disconnectedAgents, "text/html");
//			message.setContent
//	          ("<h1>The following Agents have been disconnected.  Click link below \n </h1>" + "<a href=http://s029aae.ba.ad.ssa.gov:9080</a>", "text/html");
//			
			
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}