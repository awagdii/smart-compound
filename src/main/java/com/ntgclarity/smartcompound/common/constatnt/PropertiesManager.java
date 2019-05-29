package com.ntgclarity.smartcompound.common.constatnt;

import java.util.Properties;

import org.apache.log4j.Logger;
/**
*
* @author Karim M.Fadel
*/
public class PropertiesManager {

	final static Logger logger = Logger.getLogger(PropertiesManager.class);
	static private PropertiesManager _instance = null;

	public final static String SERVER_UPLOAD_LOCATION_FOLDER = "SERVER_UPLOAD_LOCATION_FOLDER";
	public final static String SERVER_UPLOAD_LOCATION_FOLDER_TICKET = "SERVER_UPLOAD_LOCATION_FOLDER_TICKET";
	public final static String SERVER_UPLOAD_LOCATION_FOLDER_TENANT = "SERVER_UPLOAD_LOCATION_FOLDER_TENANT";
	private static Properties props;

	private PropertiesManager() {
		props = new Properties();
		try {
			props.load(PropertiesManager.class
					.getResourceAsStream("/config.properties"));

		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}
	}

	public static PropertiesManager getInstance() {
		if (_instance == null) {
			_instance = new PropertiesManager();
		}
		return _instance;
	}

	public String getData(String key) {			
		if (props != null) {
			return props.getProperty(key);
		}
		return key;
	}

}
