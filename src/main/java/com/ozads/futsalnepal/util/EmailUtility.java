package com.ozads.futsalnepal.util;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailUtility {
	
	public static void sendVerification(String to,String token){
		// Get the session object
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");

				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("futsalnepal60@gmail.com","FutsalNepal@60");// change
																							// accordingly
					}
					});
					try {
						MimeMessage message1 = new MimeMessage(session);
						message1.setFrom(new InternetAddress("futsalnepal60@gmail.com"));// change
																					// accordingly
						
						
						
						message1.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
						message1.setSubject("Verify Account");
						message1.setText("Click Here to verify your Account:" +"http://192.168.137.190:8090/futsalnepal/api/v1/customer/"+token);
						Transport.send(message1);

					} catch (MessagingException e) {
						throw new RuntimeException(e);
					}
					
				}
	
	
	
	public static void sendResetLink(String to,String token) {
		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("futsalnepal60@gmail.com","FutsalNepal@60");// change
				// accordingly
			}
		});

		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("futsalnepal60@gmail.com"));// change
																			// accordingly
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Reset Password ");
			message.setText("Click Here to Reset your password:" +"http://localhost:8080/swagger-ui.html#!/login-controller/resetPasswordUsingPOST/"+token);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void sendNewPassword(String to, String password) {
		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("futsalnepal60@gmail.com", "FutsalNepal@60");// change
			}
		});

		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("futsalnepal60@gmail.com"));// change
																			// accordingly
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Your Account ");
			
			message.setText("Your password is......." + password);

			Transport.send(message);
			System.out.printf(to);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	
	}




