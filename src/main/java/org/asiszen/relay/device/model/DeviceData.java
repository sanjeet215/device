package org.asiszen.relay.device.model;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceData implements Serializable{

	private static final long serialVersionUID = -2395109610008019678L;

	@JsonProperty("IMEI")
	private String imei;

	@JsonProperty("GPS")
	private Integer gps;

	@JsonProperty("Lat")
	private Double lat;

	@JsonProperty("Lng")
	private Double lng;

	@JsonProperty("IsKeyOn")
	private boolean isKeyOn;

	@JsonProperty("Heading")
	private Integer heading;

	@JsonProperty("Timestamp")
	private Date timestamp;

	@JsonProperty("key")
	private String key;

	public DeviceData() {
		super();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Integer getGps() {
		return gps;
	}

	public void setGps(Integer gps) {
		this.gps = gps;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public boolean isKeyOn() {
		return isKeyOn;
	}

	public void setKeyOn(boolean isKeyOn) {
		this.isKeyOn = isKeyOn;
	}

	public Integer getHeading() {
		return heading;
	}

	public void setHeading(Integer heading) {
		this.heading = heading;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "DeviceData [imei=" + imei + ", gps=" + gps + ", lat=" + lat + ", lng=" + lng + ", isKeyOn=" + isKeyOn
				+ ", heading=" + heading + ", timestamp=" + timestamp + ", key=" + key + "]";
	}

}
