package uuxia.com.library.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * The type Email.
 */
public class Email {
	//sender server info
	private String mailServerHost;
	private String mailServerPort = "25";

	// sender addr
	private String fromAddress;
	// receiver addr
	private String toAddress;
	// login info
	private String userName;
	private String password;
	// ident calidate
	private boolean validate = true;
	// subject
	private String subject;
	// email content
	private String content;
	// mail attachfile
	private String attachFile;

	/**
	 * Instantiates a new Email.
	 *
	 * @param mailServerHost the mail server host
	 * @param toAddress      the to address
	 * @param validate       the validate
	 */
	public Email(String mailServerHost, String toAddress,
				 boolean validate) {
		this(mailServerHost, "admin@amdin.eysin", toAddress, validate);
	}

	/**
	 * Instantiates a new Email.
	 *
	 * @param mailServerHost the mail server host
	 * @param fromAddress    the from address
	 * @param toAddress      the to address
	 * @param validate       the validate
	 */
	public Email(String mailServerHost, String fromAddress,
				 String toAddress, boolean validate) {
		this(mailServerHost, fromAddress, toAddress, null, null);
		this.validate = validate;
	}

	/**
	 * Instantiates a new Email.
	 *
	 * @param mailServerHost the mail server host
	 * @param fromAddress    the from address
	 * @param toAddress      the to address
	 * @param userName       the user name
	 * @param password       the password
	 */
	public Email(String mailServerHost, String fromAddress,
				 String toAddress, String userName, String password) {
		super();
		if (mailServerHost == null) {
			mailServerHost = getDomain(toAddress);
		}
		this.mailServerHost = mailServerHost;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.userName = userName;
		this.password = password;
	}

	/**
	 * Create email.
	 *
	 * @param smtp     the smtp
	 * @param to       the to
	 * @param from     the from
	 * @param password the password
	 * @return the email
	 */
	public static Email create(String smtp, String to, String from,
							   String password) {
		return new Email(smtp, from, to, from, password);
	}

	/**
	 * Create email.
	 *
	 * @param to       the to
	 * @param from     the from
	 * @param password the password
	 * @return the email
	 */
	public static Email create( String to, String from,
							   String password) {
		return new Email(null, from, to, from, password);
	}

	/**
	 * Create email.
	 *
	 * @param to     the to
	 * @param domain the domain
	 * @return the email
	 */
	public static Email create(String to, String domain) {
		return new Email(domain, to, false);
	}

	/**
	 * Create email.
	 *
	 * @param to the to
	 * @return the email
	 */
	public static Email create(String to) {
		return new Email(null, to, false);
	}

	/**
	 * Gets properties.
	 *
	 * @return the properties
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}

	/**
	 * Gets mail server host.
	 *
	 * @return the mail server host
	 */
	public String getMailServerHost() {
		return mailServerHost;
	}

	/**
	 * Sets mail server host.
	 *
	 * @param mailServerHost the mail server host
	 */
	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	/**
	 * Gets mail server port.
	 *
	 * @return the mail server port
	 */
	public String getMailServerPort() {
		return mailServerPort;
	}

	/**
	 * Sets mail server port.
	 *
	 * @param mailServerPort the mail server port
	 */
	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	/**
	 * Is validate boolean.
	 *
	 * @return the boolean
	 */
	public boolean isValidate() {
		return validate;
	}

	/**
	 * Sets validate.
	 *
	 * @param validate the validate
	 */
	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	/**
	 * Gets attach file.
	 *
	 * @return the attach file
	 */
	public String getAttachFile() {
		return attachFile;
	}

	/**
	 * Sets attach file.
	 *
	 * @param fileNames the file names
	 */
	public void setAttachFile(String fileNames) {
		this.attachFile = fileNames;
	}

	/**
	 * Gets from address.
	 *
	 * @return the from address
	 */
	public String getFromAddress() {
		return fromAddress;
	}

	/**
	 * Sets from address.
	 *
	 * @param fromAddress the from address
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**
	 * Gets password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets to address.
	 *
	 * @return the to address
	 */
	public String getToAddress() {
		return toAddress;
	}

	/**
	 * Sets to address.
	 *
	 * @param toAddress the to address
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	/**
	 * Gets user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets user name.
	 *
	 * @param userName the user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets subject.
	 *
	 * @param subject the subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets content.
	 *
	 * @param textContent the text content
	 */
	public void setContent(String textContent) {
		this.content = textContent;
	}


	private String getDomain(String email) {
		String domain = email.substring(email.indexOf('@') + 1);
		return addr.get(domain);
	}

	/**
	 * Send text mail boolean.
	 *
	 * @return the boolean
	 */
	public boolean sendTextMail() {
		Email o = this;
		PopupAuthenticator authenticator = null;
		Properties pro = getProperties();
		if (isValidate()) {
			authenticator = new PopupAuthenticator(getUserName(),
					getPassword());
		}
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			Message mailMessage = new MimeMessage(sendMailSession);
			Address from = new InternetAddress(getFromAddress(),
					"Eysin");
			mailMessage.setFrom(from);
			Address to = new InternetAddress(getToAddress(), "Eysin");
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			mailMessage.setSubject(getSubject());
			mailMessage.setSentDate(new Date());
			String mailContent = getContent();
			// mailMessage.setText(mailContent);
			mailMessage.setContent(mailContent, "text/plain");
			String attachPath = getAttachFile();
			if (attachPath != null && !attachPath.equals("")) {
				mailMessage.setFileName(attachPath);
			}
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Send html mail boolean.
	 *
	 * @return the boolean
	 */
	public boolean sendHtmlMail() {
		PopupAuthenticator authenticator = null;
		Properties pro = getProperties();
		if (isValidate()) {
			authenticator = new PopupAuthenticator(getUserName(),
					getPassword());
		}
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			Message mailMessage = new MimeMessage(sendMailSession);
			Address from = new InternetAddress(getFromAddress(),
					"Eysin");
			mailMessage.setFrom(from);
			Address to = new InternetAddress(getToAddress(), "Eysin");
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			mailMessage.setSubject(getSubject());
			mailMessage.setSentDate(new Date());
			Multipart mainPart = new MimeMultipart();
			BodyPart html = new MimeBodyPart();
			html.setContent(getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			mailMessage.setContent(mainPart, "text/plain");
			String attachPath = getAttachFile();
			if (attachPath != null && !attachPath.equals("")) {
				mailMessage.setFileName(attachPath);
			}
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	private static Map<String, String> addr = new HashMap<String, String>();
	static {
		addr.put("foxmail.com", "mx1.qq.com.");
		addr.put("vip.qq.com", "mx3.qq.com.");
		addr.put("qq.com", "mx1.qq.com.");
		addr.put("163.com", "163mx00.mxmail.netease.com.");
		addr.put("126.com", "126mx02.mxmail.netease.com.");
		addr.put("yeah.net", "yeahmx00.mxmail.netease.com.");
		addr.put("vip.163.com", "vip163mx01.mxmail.netease.com.");
		addr.put("sina.com", "freemx1.sinamail.sina.com.cn.");
		addr.put("sohu.com", "sohumx1.sohu.com.");
		addr.put("yahoo.com.cn", "mx1.mail.aliyun.com.");
		addr.put("tom.com", "tommx1.tom.com.");
		addr.put("hotmail.com", "mx1.hotmail.com.");
		addr.put("gmail.com", "alt1.gmail-smtp-in.l.google.com.");
		addr.put("189.com", "mxbiz2.qq.com.");
		addr.put("189.cn", "mta-189.21cn.com.");
		addr.put("tianya.cn", "mx.tianya.cn.");
		addr.put("live.com", "mx3.hotmail.com.");
		addr.put("wo.com.cn", "womx.wo.com.cn.");
		addr.put("139.com", "mx1.mail.139.com.");
	}
}
