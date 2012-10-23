package li.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Sender {
	private Session session;
	private String username;

	public Sender(String host, final String username, final String password) {
		this.username = username;
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.auth", true);
		session = Session.getDefaultInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		session.setDebug(true);
	}

	public void send(Mail mail) {
		if (null == mail.getFrom() && null != username) {
			mail.setFrom(username);
		}
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mail.getFrom()));
			message.addRecipient(RecipientType.TO, new InternetAddress(mail.getTo()));

			message.setSubject(mail.getSubject());
			MimeMultipart multipart = new MimeMultipart();
			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(mail.getContent(), "text/html;charset=UTF-8");
			multipart.addBodyPart(bodyPart);
			message.setContent(multipart);
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendTo(Mail mail, String... to) {
		for (String address : to) {
			mail.setTo(address);
			send(mail);
		}
	}
}