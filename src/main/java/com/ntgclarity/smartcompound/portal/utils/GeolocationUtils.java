package com.ntgclarity.smartcompound.portal.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.codehaus.jackson.map.ObjectMapper;

import com.ntgclarity.smartcompound.common.dto.geolocation.GoogleResponse;
import com.ntgclarity.smartcompound.common.dto.geolocation.Result;

public class GeolocationUtils {

	private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";

	public static GoogleResponse convertFromLatLong(String latlongString)
			throws IOException {

		URL url = new URL(URL + "?latlng="
				+ URLEncoder.encode(latlongString, "UTF-8") + "&sensor=false");

		// Open the Connection
		URLConnection connection = url.openConnection();

		InputStream inputStream = connection.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		GoogleResponse response = (GoogleResponse) mapper.readValue(
				inputStream, GoogleResponse.class);
		inputStream.close();
		return response;

	}

	public static String[] returnAddress(GoogleResponse googleResponse) {

		Result result[] = googleResponse.getResults();
		int size = result[0].getAddress_components().length;
		String city = result[0].getAddress_components()[result[0].getAddress_components().length-2].getLongName();
		String country = result[0].getAddress_components()[result[0].getAddress_components().length-1].getLongName();

		return new String[] { city, country };

	}

	public static String[] getRestfulLocation(String latlongString) {
		try {
			GoogleResponse googleResponse = convertFromLatLong(latlongString);

			return returnAddress(googleResponse);
		} catch (Exception ex) {
			return new String[] { "", "" };

		}
	}

}
