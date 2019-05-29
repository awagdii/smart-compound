package com.ntgclarity.smartcompound.common.dto.geolocation;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class AddressComponent {

	@JsonProperty("long_name")
	private String longName;
	@JsonProperty("short_name")
	private String shortName;
	
	@JsonProperty("types")
	private String[] types = new String[]{};
	
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	public String getLongName() {
		return longName;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}
