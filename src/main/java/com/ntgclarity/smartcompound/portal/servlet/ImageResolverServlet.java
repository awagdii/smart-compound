package com.ntgclarity.smartcompound.portal.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ntgclarity.smartcompound.common.constatnt.PropertiesManager;

public class ImageResolverServlet extends HttpServlet {

	final static Logger logger = Logger.getLogger(ImageResolverServlet.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String request = req.getRequestURI();
	       ServletContext cntx= getServletContext();
	       String fileType = req.getParameter("fileType");
	    // Get the absolute path of the image
	       String filePath = "";
	       if(fileType.equalsIgnoreCase("1"))
	    	   filePath = PropertiesManager.getInstance().getData(PropertiesManager.SERVER_UPLOAD_LOCATION_FOLDER_TICKET);
	       else if(fileType.equalsIgnoreCase("2"))
	    	   filePath = PropertiesManager.getInstance().getData(PropertiesManager.SERVER_UPLOAD_LOCATION_FOLDER_TENANT);
	       else 
	    	   filePath = PropertiesManager.getInstance().getData(PropertiesManager.SERVER_UPLOAD_LOCATION_FOLDER);
	      String filename = req.getParameter("fileName");
	      // retrieve mimeType dynamically
	      // supposed filename include seperator
	      String mime = cntx.getMimeType(filePath+filename);
	      if (mime == null) {
	        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        return;
	      } 

	      resp.setContentType(mime);
	      File file = new File(filePath+filename);
	      resp.setContentLength((int)file.length());

	      FileInputStream in = new FileInputStream(file);
	      OutputStream out = resp.getOutputStream();

	      // Copy the contents of the file to the output stream
	       byte[] buf = new byte[1024];
	       int count = 0;
	       while ((count = in.read(buf)) >= 0) {
	         out.write(buf, 0, count);
	      }
	    out.close();
	    in.close();

	}
	
}
