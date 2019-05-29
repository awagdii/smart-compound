package com.ntgclarity.smartcompound.portal.velocity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.ntgclarity.smartcompound.common.entity.Bill;


public class BillVelocity {
	public static void initiate(Bill bill) {
		try {

			VelocityEngine ve = new VelocityEngine();
			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			ve.setProperty("classpath.resource.loader.class",
					ClasspathResourceLoader.class.getName());
			ve.init();

			Template t = ve.getTemplate("../templates/billTemplate.vm");
//			Template template = velocityEngine.getTemplate("../templates/forgetPassword.vm")

			VelocityContext context = new VelocityContext();
			
			
			context.put("bill",bill);
			context.put("billDetails",bill.getBillDetails());
			StringWriter writer = new StringWriter();
			t.merge(context, writer);

//			System.out.println(writer.toString());
			OutputStream file;

			file = new FileOutputStream(new File("E:\\BillTemplate.pdf"));
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
