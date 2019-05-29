package com.ntgclarity.smartcompound.ws.controller;

import java.io.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DownloadFileServlet")
public class DownloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String fileName = request.getParameter("fileName");
//		if (fileName == null || fileName.equals("")) {
//			throw new ServletException("File Name can't be null or empty");
//		}
//		File file = new File(request.getServletContext().getAttribute("FILES_DIR")
//				+ File.separator + fileName); // listener 
		String fileName = "MyPngImageFile";
		File file = new File("C://Users/AndDeve/Desktop/Upload_Files/3.png");
		
		if (!file.exists()) {
			throw new ServletException("File doesn't exists on server.");
		}
//		System.out.println("File location on server::" + file.getAbsolutePath());
		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
		String mimeType = ctx.getMimeType(file.getAbsolutePath());
		response.setContentType(mimeType != null ? mimeType: "application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\"");

		ServletOutputStream os = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read = 0;
		while ((read = fis.read(bufferData)) != -1) {
			os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
//		System.out.println("File downloaded at client successfully");
	}
}
