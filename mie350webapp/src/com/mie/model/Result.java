package com.mie.model;

import java.util.Set;
import java.util.TreeSet;

// this class holds information about the final results that will be displayed to the user to allow them to choose which store to go to
// Result holds information about store details, the price for the user's shopping cart at that particular store, 
// the distance from the user to that store, the ETA from the user to that store, the user's chosen travel method to get to the store, 
// as well as how the user wants to rank the results (i.e. preference by price, ETA, or both, in which case the time value is also considered)

// this class implements Comparable<Result> so that a TreeSet of Result objects will be in order based on user's preference

public class Result implements Comparable<Result> {
	
	private Store storeDetails;
	private double price;
	private double distance;
	private int ETA;
	private String travelMethod;
	private String preference;
	private double timeValue;
	
	public Result(Store store, double price, double distance, String travelMethod, String preference, double timeValue) {
		this.setStoreDetails(store);
		this.setPrice(price);
		this.setDistance(distance);
		this.setTravelMethod(travelMethod);
		this.setPreference(preference);
		this.setTimeValue(timeValue);
		
		this.makeETA(distance, travelMethod);
	}
	
	// a metric is used when the user prefers both time and price, thus both are combined into a metric via "how much a user values an hour"
	public double getMetric() {
		return this.getTimeValue()*this.getETA()/60 + this.getPrice();
	}

	public Store getStoreDetails() {
		return storeDetails;
	}

	public void setStoreDetails(Store storeDetails) {
		this.storeDetails = storeDetails;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(Result other) {

		// if user prefers price, then rank by price
		if (this.getPreference().equalsIgnoreCase("price")) 
			return compareByPrice(other);
		
		// if user prefers time, then rank by ETA
		else if (this.getPreference().equalsIgnoreCase("time"))
			return compareByTime(other);
		
		// if user prefers both, then rank by metric
		else if (this.getPreference().equalsIgnoreCase("both"))
			return compareByMetric(other);
		
		else
			return 0;
		
	}
	
	// this function ranks Result objects by metric first, then price, then franchise name, then id; if IDs are same, then the stores are the same.
	private int compareByMetric(Result other) {

		if (this.getMetric() < other.getMetric())
			return -1;
		else if (this.getMetric() > other.getMetric())
			return 1;
		
		else {
			if (this.getPrice() < other.getPrice()) 
				return -1;
			else if (this.getPrice() > other.getPrice()) 
				return 1;
			
			else {
				if (this.getStoreDetails().getFranchise().compareToIgnoreCase(other.getStoreDetails().getFranchise()) < 0)
					return -1;
				else if (this.getStoreDetails().getFranchise().compareToIgnoreCase(other.getStoreDetails().getFranchise()) > 0)
					return 1;
				
				else {
					if (this.getStoreDetails().getStoreID() < other.getStoreDetails().getStoreID())
						return -1;
					else if (this.getStoreDetails().getStoreID() > other.getStoreDetails().getStoreID())
						return 1;
					
					else
						return 0;
				}
			}
		}
	}

	// this function ranks Result objects by ETA first, then price, then franchise name, then id; if IDs are same, then the stores are the same.
	private int compareByTime(Result other) {

		if (this.getETA() < other.getETA())
			return -1;
		else if (this.getETA() > other.getETA())
			return 1;
		
		else {
			if (this.getPrice() < other.getPrice()) 
				return -1;
			else if (this.getPrice() > other.getPrice()) 
				return 1;
			
			else {
				if (this.getStoreDetails().getFranchise().compareToIgnoreCase(other.getStoreDetails().getFranchise()) < 0)
					return -1;
				else if (this.getStoreDetails().getFranchise().compareToIgnoreCase(other.getStoreDetails().getFranchise()) > 0)
					return 1;
				
				else {
					if (this.getStoreDetails().getStoreID() < other.getStoreDetails().getStoreID())
						return -1;
					else if (this.getStoreDetails().getStoreID() > other.getStoreDetails().getStoreID())
						return 1;
					
					else
						return 0;
				}
			}
		}
	}

	// this function ranks Result objects by price first, then ETA, then franchise name, then id; if IDs are same, then the stores are the same.
	private int compareByPrice(Result other) {
		
		if (this.getPrice() < other.getPrice())
			return -1;
		else if (this.getPrice() > other.getPrice())
			return 1;
		
		else {
			if (this.getETA() < other.getETA()) 
				return -1;
			else if (this.getETA() > other.getETA()) 
				return 1;
			
			else {
				if (this.getStoreDetails().getFranchise().compareToIgnoreCase(other.getStoreDetails().getFranchise()) < 0)
					return -1;
				else if (this.getStoreDetails().getFranchise().compareToIgnoreCase(other.getStoreDetails().getFranchise()) > 0)
					return 1;
				
				else {
					if (this.getStoreDetails().getStoreID() < other.getStoreDetails().getStoreID())
						return -1;
					else if (this.getStoreDetails().getStoreID() > other.getStoreDetails().getStoreID())
						return 1;
					
					else
						return 0;
				}
			}
		}
	}

	// toString() used for debugging
	@Override
	public String toString() {
		return "\n{Store: " + this.getStoreDetails().getFranchise()
				+ "\nStoreID: " + this.getStoreDetails().getStoreID()
				+ "\nLocation: " + this.getStoreDetails().getStreetAddress()
				+ "\nPrice: " + this.getPrice() 
				+ "\nETA: " + this.getETA()
				+ "\nMetric: " + this.getMetric()
				+ "}"; //TEMPORARY TOSTRING RETURN
		
	}

	public String getTravelMethod() {
		return travelMethod;
	}

	public void setTravelMethod(String travelMethod) {
		this.travelMethod = travelMethod;
	}

	public int getETA() {
		return ETA;
	}

	// this function calculates an approximate ETA based on the distance and travel method 
	public void makeETA(double distance, String travelMethod) {
		
		if (travelMethod.equalsIgnoreCase("walk"))
			this.ETA = ETAByWalk(distance);
		
		else if (travelMethod.equalsIgnoreCase("car"))
			this.ETA = ETAByCar(distance);
		
		else if (travelMethod.equalsIgnoreCase("transit"))
			this.ETA = ETAByTransit(distance);
		
		else if (travelMethod.equalsIgnoreCase("bike"))
			this.ETA = ETAByBike(distance);
		
		
	}
	
	// this function approximates ETA based on the distance for travelling by "car"
	public int ETAByCar(double distance) {
		if (distance >= 15.5) 
			return (int)(60*(distance + 10)/70);
		
		else if (distance < 15.5 && distance >= 1.15) 
			return (int)(60*(distance + 3)/50);
		
		else if (distance < 1.15 && distance >= 0.15) 
			return (int)(60*(distance + 0.3)/5.4);
		
		else 
			return (int)(60*distance/5.4);
	}
	
	// this function approximates ETA based on the distance for travelling by "bike"
	public int ETAByBike(double distance) {
		if (distance >= 15.5) 
			return (int)(60*(distance + 6.5)/15.5);
		
		else if (distance < 15.5 && distance >= 1.15) 
			return (int)(60*(distance + 1.67)/15.5);
		
		else if (distance < 1.15 && distance >= 0.15) 
			return (int)(60*(distance + 0.15)/15.5);
		
		else 
			return (int)(60*distance/5.4);
	}
	
	// this function approximates ETA based on the distance for travelling by "walk"ing
	public int ETAByWalk(double distance) {
		if (distance >= 15.5) 
			return (int)(60*(distance + 10)/5.4);
		
		else if (distance < 15.5 && distance >= 1.15) 
			return (int)(60*(distance*2.177 - 1.089)/5.4);
		
		else if (distance < 1.15 && distance >= 0.15) 
			return (int)(60*(distance + 0.3)/5.4);
		
		else 
			return (int)(60*distance/5.4);
	}
	
	// this function approximates ETA based on the distance for travelling by "transit"
	public int ETAByTransit(double distance) {
		if (distance >= 15.5) 
			return (int)(ETAByBike(distance)*1.75);
		
		else if (distance < 15.5 && distance >= 1.15) 
			return (int)ETAByBike(distance);
		
		else if (distance < 1.15 && distance >= 0.15) 
			return (int)(60*(distance + 0.3)/5.4);
		
		else 
			return (int)(60*distance/5.4);
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	public double getTimeValue() {
		return timeValue;
	}

	public void setTimeValue(double timeValue) {
		this.timeValue = timeValue;
	}
	
}
