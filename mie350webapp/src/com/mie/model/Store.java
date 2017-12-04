package com.mie.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

// this class holds information about a particular store - it's ID, franchise name, street address, region, postal code, phone number,
// as well as information such as latitude and longitude and the distance from the user's location
// it also holds information about it's hours in two different formats (a complete HashMap that maps each day to a range of hours) 
// and a list of Strings that represent store hours in short form (e.g. "Mon-Fri : 09:00 - 22:00")

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

	// this function converts a complete HashMap representation of store hours (which maps each day and is unordered)
	// into a compact form, such as "Mon-Sun: 9:00-22:00"
	public void makeHoursCompact() {
		
		hoursCompact = new ArrayList<String>(); // a list will hold the compact form. e.g. "Mon-Fri: 9:00-22:00","Sat-Sun: 12:00-23:00"
		
		String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		
		// HOW IT WORKS:
		
		// start on monday (set startDay to "Mon")
		// get startTime and get endTime
		
		// the startDay, startTime and endTime are intialized.
		// the endDay is initialized to the startDay
				
		// the day after the startDay is accessed (i.e. currDay)
		// if currDay's time is the same as startDay, then set currDay as endDay (to end the string if the next day's time is different), and the next day becomes currDay
		// if currDay's time is different from startDay, then one string is completed in the form of "startDay - endDay : startTime - endTime" and inserted into the list
		// if startDay and endDay are the same, then the string is in the form of "startDay : startTime - endTime" and inserted into the list
		
		String startDay = "Mon";
		String startTimeFull = hours.get(days[0])[0].toString(); // e.g. 9:00:00 as in database
		String startTime = startTimeFull.substring(0, startTimeFull.length() - 3); // shortened to 9:00
		String endTimeFull = hours.get(days[0])[1].toString();
		String endTime = endTimeFull.substring(0, startTimeFull.length() - 3);
		
		String currStartTimeFull = "";
		String currEndTimeFull = "";
		
		String line = "";
		
		//line += startDay + " - ";
		
		
		String endDay = startDay;
		String currDay = "";
		
		for(int i = 1; i < days.length; i++) {
			currDay = days[i].substring(0, 3);
			currStartTimeFull = hours.get(days[i])[0].toString();
			currEndTimeFull = hours.get(days[i])[1].toString();
			
			// if the currDay's times match startDay's, then set a new endDay 
			if (startTimeFull.equalsIgnoreCase(currStartTimeFull) && endTimeFull.equalsIgnoreCase(currEndTimeFull)) {
				endDay = currDay;
				
				// if the currDay/endDay is Sunday, then no more looping after, so break after adding the last String into list
				if (i == days.length - 1) {
					line = startDay + " - " + endDay + ": " + startTime + " - " + endTime;
					hoursCompact.add(line);
					break;
				}
				
				// if the currDay is not Sunday, then keep looping; start iteration from the top
				continue;
			}
			
			// if currDay's time doesn't match startDay's, that signifies the end of a string and a beginning of a new one
			else {
				
				// if startDay and endDay are the same, then the string is only for one day
				if (startDay.equals(endDay)) {
					line = endDay + ": " + startTime + " - " + endTime;
				}
				
				// if startDay and endDay are different, then the string is for a range of days
				else {
					line = startDay + " - " + endDay + ": " + startTime + " - " + endTime;
				}
				
				hoursCompact.add(line); // the string is added to the list
				
				// the process now repeats from the first step; startDay is initialized to currDay, etc.
				startDay = currDay;
				endDay = startDay;
				
				startTimeFull = currStartTimeFull;
				endTimeFull = currEndTimeFull;
				startTime = startTimeFull.substring(0, startTimeFull.length() - 3);
				endTime = endTimeFull.substring(0, startTimeFull.length() - 3);
				
				// if currDay is sunday and it reached this point, then that means sunday is on its own
				if (i == days.length - 1) {
					line = endDay + ": " + startTime + " - " + endTime;
					hoursCompact.add(line);
					break;
				}
				
			}
		}		
	}

	public ArrayList<String> getHoursCompact() {
		return hoursCompact;
	}

}
