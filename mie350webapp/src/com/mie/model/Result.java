package com.mie.model;

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
	
	
}
