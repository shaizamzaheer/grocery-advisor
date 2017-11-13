package com.mie.model;

import java.util.Set;
import java.util.TreeSet;

public class Result implements Comparable<Result> {
	
	private Store storeDetails;
	private double price;
	private double distance;
	private final double distanceToPrice = 1;
	
	public Result(Store store, double price, double distance) {
		this.setStoreDetails(store);
		this.setPrice(price);
		this.setDistance(distance);
	}
	
	public double getMetric(double price, double distance) {
		return price + distance*distanceToPrice;
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
		double thisMetric = this.getMetric(this.getPrice(), this.getDistance());
		double otherMetric = other.getMetric(other.getPrice(), other.getDistance());
		
		if (thisMetric < otherMetric) return -1; 
		else if (thisMetric > otherMetric) return 1;
		else if (this.getPrice() < other.getPrice()) return -1;
		else if (this.getPrice() > other.getPrice()) return 1;
		else return 0;
		
	}
	
	@Override
	public String toString() {
		return "{StoreID: " + this.getStoreDetails().getStoreID() + ", Price: " + this.getPrice() + ", Distance: " + this.getDistance() + "}"; //TEMPORARY TOSTRING RETURN
		
	}
	
	public static void main(String[] args) {
		Result r1 = new Result(new Store(1, 0, 0), 10, 5); //metric = 15; price = 5
		Result r2 = new Result(new Store(2, 0, 0), 5, 5); //10; 5
		Result r3 = new Result(new Store(3, 0, 0), 10, 10); //20; 10
		Result r4 = new Result(new Store(4, 0, 0), 19, 1); //20; 19
		Result r5 = new Result(new Store(5, 0, 0), 5, 11); //16; 5
		
		//:. order should be... r2, r1, r5, r3, r4
		
		Set<Result> results = new TreeSet<Result>();
		results.add(r1);
		results.add(r2);
		results.add(r3);
		results.add(r4);
		results.add(r5);
		
		System.out.println(results); //2, 1, 5, 3, 4 --> ORDER WORKS
		
	}
	
}
