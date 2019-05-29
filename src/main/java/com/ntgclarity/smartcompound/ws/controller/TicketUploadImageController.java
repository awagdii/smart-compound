package com.ntgclarity.smartcompound.ws.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.ntgclarity.smartcompound.common.constatnt.PropertiesManager;
import com.ntgclarity.smartcompound.common.dto.SmartCompoundResponse;
import com.ntgclarity.smartcompound.common.entity.Ticket;
import com.ntgclarity.smartcompound.common.entity.TicketImage;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;

/**
 * 
 * @author Karim M.Fadel
 */
@Path("/ticketuploadimagecontroller")
public class TicketUploadImageController extends AbstractController {

	final static Logger logger = Logger
			.getLogger(TicketUploadImageController.class);

	// private static final String SERVER_UPLOAD_LOCATION_FOLDER =
	// "C://Users/AndDeve/Desktop/Upload_Files/";

	// @GET
	// @Path("/download/{imagename}")
	// @Produces({"image/png", "image/jpg", "image/gif"})
	// public Response downloadImageFile(@PathParam("imagename") String
	// imageName){
	// // set file (and path) to be download
	// File file = new
	// File("C://Users/AndDeve/Desktop/Upload_Files/"+imageName+".png");
	//
	// ResponseBuilder responseBuilder = Response.ok((Object) file);
	// responseBuilder.header("Content-Disposition",
	// "attachment; filename=\"MyPngImageFile.png\"");
	// return responseBuilder.status(200).build();
	// }
	//
	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input,
			@Context HttpHeaders headers) {
		try {
			if (headers.getRequestHeader("name") == null)
				throw new SmartCompoundException();
			String imageId = headers.getRequestHeader("name").get(0);
			String fileName = "";

			Map<String, List<InputPart>> formParts = input.getFormDataMap();

			if (formParts.get("photo") == null)
				throw new SmartCompoundException();
			List<InputPart> inPart = formParts.get("photo");

			for (InputPart inputPart : inPart) {

				// Retrieve headers, read the Content-Disposition header to
				// obtain the original name of the file
				MultivaluedMap<String, String> headers_body = inputPart
						.getHeaders();
				fileName = parseFileName(headers_body)+".jpg";

				logger.info(fileName);

				// Handle the body of that part with an InputStream
				InputStream istream = inputPart
						.getBody(InputStream.class, null);

				saveFile(istream, insertTicketImage(imageId,fileName));

			}
		} catch (SmartCompoundException e) {
			return Response.status(400).entity("Failure").build();
		} catch (IOException e) {
			return Response.status(400).entity("Failure").build();
		}
		return Response.status(200).entity("Sucess").build();
	}

	private String insertTicketImage(String imageId,String imageName)
			throws SmartCompoundException {
//		String[] tmp = imageName.split("\\.");
		Ticket ticket = new Ticket();
		ticket.setId(Long.parseLong(imageId, 10));
		TicketImage ticketImage = new TicketImage();
		ticketImage.setTicket(ticket);
		String imageURL = RandomStringUtils.randomNumeric(8) + imageName;
		ticketImage.setImageUrl(imageURL);
		getSmartCompoundManagement().insertTicketImage(ticketImage);
		return PropertiesManager.getInstance().getData(
				PropertiesManager.SERVER_UPLOAD_LOCATION_FOLDER_TICKET)
				+ imageURL;
	}

	// Parse Content-Disposition header to get the original file name
	private String parseFileName(MultivaluedMap<String, String> headers) {

		String[] contentDispositionHeader = headers.getFirst(
				"Content-Disposition").split(";");

		for (String name : contentDispositionHeader) {

			if ((name.trim().startsWith("filename"))) {

				String[] tmp = name.split("=");

				String fileName = tmp[1].trim().replaceAll("\"", "");

				return fileName;
			}
		}
		return "randomName";
	}

	// save uploaded file to a defined location on the server
	private void saveFile(InputStream uploadedInputStream, String serverLocation) {

		try {
			OutputStream outpuStream = new FileOutputStream(new File(
					serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}