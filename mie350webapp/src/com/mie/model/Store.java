package com.mie.model;

import java.sql.Time;
import java.util.HashMap;

public class Store {
	
	private int storeID;
	private String franchise;
	private String street_address;
	private String region;
	private String postal_code;
	private String phone;
	
	private double lat;
	private double lon;
	
	//TODO: store hours, possibly as HashMap<String, Time?[2]> where String -> DayOfWeek and Integer[2] is an array of 2 times...
	private HashMap<String, Time[]> hours;
	
	
	
	public Store(int storeID, double lat, double lon) {
		this.setStoreID(storeID);
		this.setLat(lat);
		this.setLon(lon);
	}
	
	public Store(int storeID, String franchise, String street_address, String region, String postal_code, String phone) {
		this.setStoreID(storeID);
		this.setFranchise(franchise);
		this.setStreetAddress(street_address);
		this.setRegion(region);
		this.setPostalCode(postal_code);
		this.setPhone(phone);
		
		hours = new HashMap<String, Time[]>();
	}
	
	public String getFranchise() {
		return franchise;
	}
	public void setFranchise(String franchise) {
		this.franchise = franchise;
	}
	public int getStoreID() {
		return storeID;
	}
	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public String getStreetAddress() {
		return street_address;
	}
	public void setStreetAddress(String street_address) {
		this.street_address = street_address;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostalCode() {
		return postal_code;
	}

	public void setPostalCode(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void addDayAndHours(String dayOfWeek, Time[] times) {
		hours.put(dayOfWeek, times);
	}
	
	public HashMap<String, Time[]> getHours() {
		return hours;
	}
	
}
