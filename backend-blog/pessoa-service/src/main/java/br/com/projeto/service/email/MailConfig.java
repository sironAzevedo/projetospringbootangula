package br.com.projeto.service.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.projeto.projetoCommon.util.SimpleAuth;
import br.com.projeto.projetoCommon.valueobjects.DestinatarioVO;
import br.com.projeto.projetoCommon.valueobjects.Mensagem;

public class MailConfig {

	protected String EMAIL_FROM = null;
	protected String EMAIL_SENHA = null;
	private String MAIL_SMTP_HOST = null;
	private String MAIL_SMTP_PORT = null;
	private String MAIL_TRANSPORT_PROTOCOL = null;
	private String MAIL_SMTP_STARTTLS_ENABLE = null;
	private String MAIL_SMTP_AUTH = null;
	private String MAIL_SMTP_DEBUG = null;
	private String MAIL_SMTP_SOCKET_FACTORY_PORT = null;
	private String MAIL_SMTP_SOCKET_FACTORY_CLASS = null;
	private String MAIL_SMTP_SOCKET_FACTORY_FALLBACK = null;

	public MailConfig() {
		super();
		getEmailProperties();
	}

	public void sendEmail(Mensagem mensagem) {

		Message msg = new MimeMessage(getSession());

		try {
			msg.setFrom(new InternetAddress(mensagem.getRemetente()));
			msg.setRecipients(Message.RecipientType.TO, getAddress(mensagem.getDestinatarios()));
			msg.setSubject(mensagem.getAssunto());

			if (mensagem.isAnexo()) {
				msg.setContent(getAnexo(msg, mensagem.getTexto()));
			} else {
				msg.setText(mensagem.getTexto());
			}

			this.getTransport(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Session getSession() {
		Session session = null;
		session = Session.getDefaultInstance(configPropertiesEmail(), auth());
		session.setDebug(true);

		return session;
	}

	protected SimpleAuth auth() {
		SimpleAuth auth = null;
		auth = new SimpleAuth(EMAIL_FROM, EMAIL_SENHA);
		return auth;
	}

	public static Properties getProp() throws FileNotFoundException {
		Properties props = null;
		try {
			props = new Properties();
			FileInputStream file;
			file = new FileInputStream("src\\main\\resources\\emailConfig\\email.properties");
			props.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return props;

	}

	public Properties configPropertiesEmail() {
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);
		properties.put("mail.smtp.starttls.enable", MAIL_SMTP_STARTTLS_ENABLE);
		properties.put("mail.smtp.host", MAIL_SMTP_HOST);
		properties.put("mail.smtp.auth", MAIL_SMTP_AUTH);
		properties.put("mail.smtp.user", EMAIL_FROM);
		properties.put("mail.debug", MAIL_SMTP_DEBUG);
		properties.put("mail.smtp.port", MAIL_SMTP_PORT);
		properties.put("mail.smtp.socketFactory.port", MAIL_SMTP_SOCKET_FACTORY_PORT);
		properties.put("mail.smtp.socketFactory.class", MAIL_SMTP_SOCKET_FACTORY_CLASS);
		properties.put("mail.smtp.socketFactory.fallback", MAIL_SMTP_SOCKET_FACTORY_FALLBACK);

		return properties;
	}

	public void getTransport(Message message) {
		Transport transport;

		try {
			transport = getSession().getTransport("smtp");
			transport.connect("smtp.googlemail.com", EMAIL_FROM, EMAIL_SENHA);
			message.saveChanges();
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public InternetAddress[] getAddress(Set<DestinatarioVO> destinatarios) throws AddressException {
		List<DestinatarioVO> destinatario = new ArrayList<>(destinatarios);
		InternetAddress[] address = new InternetAddress[destinatario.size()];
		for (int i = 0; i < destinatario.size(); i++) {
			address[i] = new InternetAddress(destinatario.get(i).getEmail());
		}
		return address;
	}

	private Multipart getAnexo(Message msg, String mensagem) {
		MimeBodyPart mpb = new MimeBodyPart();
		Multipart mp = new MimeMultipart();
		MimeBodyPart mbpAnexo = new MimeBodyPart();
		try {

			mpb.setText(mensagem);
			mp.addBodyPart(mpb);
			String Endereco_Anexo = "C:\\Users\\sironazevedo\\Documents\\SIRON AZEVEDO SANTOS DA SILVA.pdf";
			File Arquivo = new File(Endereco_Anexo);

			mbpAnexo.setDataHandler(new DataHandler(new FileDataSource(Arquivo)));
			mbpAnexo.setFileName(Arquivo.getName());
			mp.addBodyPart(mbpAnexo);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return mp;
	}

	private void getEmailProperties() {
		try {
			EMAIL_FROM = getProp().getProperty("mail.username");
			EMAIL_SENHA = getProp().getProperty("mail.password");
			MAIL_SMTP_HOST = getProp().getProperty("mail.smtp.host");
			MAIL_SMTP_PORT = getProp().getProperty("mail.smtp.port");
			MAIL_TRANSPORT_PROTOCOL = getProp().getProperty("mail.transport.protocol");
			MAIL_SMTP_DEBUG = getProp().getProperty("mail.smtp.debug");
			MAIL_SMTP_AUTH = getProp().getProperty("mail.smtp.auth");
			MAIL_SMTP_STARTTLS_ENABLE = getProp().getProperty("mail.smtp.starttls.enable");
			MAIL_SMTP_SOCKET_FACTORY_PORT = getProp().getProperty("mail.smtp.socketFactory.port");
			MAIL_SMTP_SOCKET_FACTORY_CLASS = getProp().getProperty("mail.smtp.socketFactory.class");
			MAIL_SMTP_SOCKET_FACTORY_FALLBACK = getProp().getProperty("mail.smtp.socketFactory.fallback");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
