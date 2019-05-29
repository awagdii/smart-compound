package com.ntgclarity.smartcompound.common.dto.mail;

import java.io.StringWriter;
import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.ntgclarity.smartcompound.common.entity.Tenant;

/**
 * author by Karim M.Fadel
 **/

public class Mailer {
	
	final static Logger logger = Logger.getLogger(Mailer.class);

	private MailSender mailSender;

	private VelocityEngine velocityEngine;

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String email, Tenant tenant) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("compoundCompound@gmail.com"); 
		message.setTo(email);
		message.setSubject("Reset Password");
// 		changeUsernameOfMailSender(mailSender, tenant.getCompound().getFirstEmail());
		Template template = velocityEngine
				.getTemplate("../templates/forgetPassword.vm");

		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("firstName", tenant.getFirstName());
		velocityContext.put("lastName", tenant.getLastName());
		velocityContext.put("password", tenant.getPassword());
		velocityContext.put("compoundName", tenant.getCompound()
				.getCompoundName());

		StringWriter stringWriter = new StringWriter();

		template.merge(velocityContext, stringWriter);

		message.setText(stringWriter.toString());

		 mailSender.send(message);
	}

//	void changeUsernameOfMailSender(MailSender mailSender, String compoundEmail) {
//		
//		System.out.println("****************    Fields     ******************");
//		for (Field field : mailSender.getClass().getDeclaredFields()) {
//			if(field.getName().equalsIgnoreCase("username")){
//				try {
//					field.setAccessible(true);
//					field.set( mailSender, compoundEmail);
//					break;
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					e.printStackTrace();
//				} catch (SecurityException e) {
//					e.printStackTrace();
//				}
//			}
//			if(field.getName().equalsIgnoreCase("password")){
//				try {
//					field.setAccessible(true);
//					field.set( mailSender, compoundEmail);
//					break;
//				} catch (IllegalArgumentException | IllegalAccessException e) {
//					e.printStackTrace();
//				} catch (SecurityException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
}
