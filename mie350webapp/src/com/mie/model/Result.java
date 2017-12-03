package com.mie.model;

import java.util.Set;
import java.util.TreeSet;

public class Result implements Comparable<Result> {
	
	private Store storeDetails;
	private double price;
	private double distance;
	private final double distanceToPrice = 1;
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

		if (this.getPreference().equalsIgnoreCase("price")) 
			return compareByPrice(other);
		
		else if (this.getPreference().equalsIgnoreCase("time"))
			return compareByTime(other);
		
		else if (this.getPreference().equalsIgnoreCase("both"))
			return compareByMetric(other);
		
		else
			return 0;
		
	}
	
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

	@Override
	public String toString() {
		return "{StoreID: " + this.getStoreDetails().getStoreID() + ", Price: " + this.getPrice() + ", Distance: " + this.getDistance() + "}"; //TEMPORARY TOSTRING RETURN
		
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

	public void makeETA(double distance, String travelMethod) {
		
		if (distance >= 15.5) 
			this.ETA = (int)(60*(distance + 10)/70);
		
		else if (distance < 15.5 && distance >= 1.15) 
			this.ETA = (int)(60*(distance + 3)/50);
		
		else if (distance < 1.15 && distance >= 0.15) 
			this.ETA = (int)(60*(distance + 0.3)/5.4);
		
		else 
			this.ETA = (int)(60*distance/5.4);
		
		
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
	
//	public static void main(String[] args) {
//		Result r1 = new Result(new Store(1, 0, 0), 10, 5); //metric = 15; price = 5
//		Result r2 = new Result(new Store(2, 0, 0), 5, 5); //10; 5
//		Result r3 = new Result(new Store(3, 0, 0), 10, 10); //20; 10
//		Result r4 = new Result(new Store(4, 0, 0), 19, 1); //20; 19
//		Result r5 = new Result(new Store(5, 0, 0), 5, 11); //16; 5
//		
//		//:. order should be... r2, r1, r5, r3, r4
//		
//		Set<Result> results = new TreeSet<Result>();
//		results.add(r1);
//		results.add(r2);
//		results.add(r3);
//		results.add(r4);
//		results.add(r5);
//		
//		System.out.println(results); //2, 1, 5, 3, 4 --> ORDER WORKS
//		
//	}
	
}
