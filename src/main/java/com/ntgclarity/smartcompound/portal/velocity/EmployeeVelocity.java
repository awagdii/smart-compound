package com.ntgclarity.smartcompound.portal.velocity;

import java.io.StringWriter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.ntgclarity.smartcompound.common.entity.Employee;


public class EmployeeVelocity {

	

	public static void initiate(Employee employee) {
		try {

			VelocityEngine ve = new VelocityEngine();
			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			ve.setProperty("classpath.resource.loader.class",
					ClasspathResourceLoader.class.getName());
			ve.init();

			Template t = ve.getTemplate("/templates/employeeTemplate.vm");

			VelocityContext context = new VelocityContext();
//			context.put("firstName", employee.getFirstName());
//			context.put("lastName", employee.getLastName());
//			context.put("userName", employee.getUsername());
//			context.put("middleName", employee.getMiddleName());
//			context.put("address", employee.getAddress());
//			context.put("status", employee.getStatus());
//			context.put("job", employee.getStatus());
//			context.put("email", employee.getEmail());
//			context.put("phoneNumber", employee.getPhoneNumber1());
//			context.put("gender",employee.getGender());

			StringWriter writer = new StringWriter();
			t.merge(context, writer);

//			System.out.println(writer.toString());
			OutputStream file;

			file = new FileOutputStream(new File("E:\\employeeTemplate.pdf"));
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();
			HTMLWorker htmlWorker = new HTMLWorker(document);
			htmlWorker.parse(new StringReader(writer.toString()));

			document.close();
			file.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (DocumentException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
