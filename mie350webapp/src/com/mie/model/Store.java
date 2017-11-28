package com.mie.model;

import java.sql.Time;
import java.util.ArrayList;
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
	
	private double distance;
	
	//TODO: store hours, possibly as HashMap<String, Time?[2]> where String -> DayOfWeek and Integer[2] is an array of 2 times...
	private HashMap<String, Time[]> hours;
	
	private ArrayList<String> hoursCompact;
	
	
	
	public Store(int storeID, double lat, double lon, double distance) {
		this.setStoreID(storeID);
		this.setLat(lat);
		this.setLon(lon);
		this.setDistance(distance);
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

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void makeHoursCompact() {
		
		hoursCompact = new ArrayList<String>();
		
		String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		
		String startDay = "Mon";
		String startTimeFull = hours.get(days[0])[0].toString();
		String startTime = startTimeFull.substring(0, startTimeFull.length() - 3);
		String endTimeFull = hours.get(days[0])[1].toString();
		String endTime = endTimeFull.substring(0, startTimeFull.length() - 3);
		
		String currStartTimeFull = "";
		String currEndTimeFull = "";
		
		String line = "";
		
		line += startDay + " - ";
		
		String endDay = startDay;
		String currDay = "";
		
		for(int i = 1; i < days.length; i++) {
			currDay = days[i].substring(0, 3);
			currStartTimeFull = hours.get(days[i])[0].toString();
			currEndTimeFull = hours.get(days[i])[1].toString();
			
			if (startTimeFull.equalsIgnoreCase(currStartTimeFull) && endTimeFull.equalsIgnoreCase(currEndTimeFull)) {
				endDay = currDay;
				
				if (i == days.length - 1) {
					line = startDay + " - " + endDay + ": " + startTime + " - " + endTime;
					hoursCompact.add(line);
					break;
				}
				
				continue;
			}
			
			else {
				
				if (startDay.equals(endDay)) {
					line = endDay + ": " + startTime + " - " + endTime;
				}
				
				else {
					line = startDay + " - " + endDay + ": " + startTime + " - " + endTime;
				}
				
				hoursCompact.add(line);
				
				startDay = currDay;
				endDay = startDay;
				
				startTimeFull = currStartTimeFull;
				endTimeFull = currEndTimeFull;
				startTime = startTimeFull.substring(0, startTimeFull.length() - 3);
				endTime = endTimeFull.substring(0, startTimeFull.length() - 3);
				
				if (i == days.length - 1) {
					line = endDay + ": " + startTime + " - " + endTime;
					hoursCompact.add(line);
					break;
				}
				
			}
		}
		//start on monday (set startDay to "Mon")
		//get startTime and get endTime
		
		//go to tuesday (set endDay to "Tue")
		//if startTime and endTime are same as previous, go to wednesday.
		//if startTime and endTime are different than previous
			//then establish the string > store in arraylist > go to Wednesday, start on wednesday (set startDay to "Wed")
		
	}

	public ArrayList<String> getHoursCompact() {
		return hoursCompact;
	}

}
