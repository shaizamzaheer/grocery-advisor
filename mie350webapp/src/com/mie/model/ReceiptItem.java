package com.mie.model;

import java.sql.Date;

// this class extends CartItem (which extends Item)
// thus, ReceiptItem holds information about a particular item and its quantity, 
// in addition to its price and the date that the (sale) price ends if available (for a particular store)

public class ReceiptItem extends CartItem {
	private double price;
	private Date saleEnd;
	
	public ReceiptItem(int itemID, String itemName, String amount, int quantity, double price) {
		super(itemID, itemName, amount, quantity);
		this.setPrice(price);
	}
	
	public ReceiptItem(int itemID, String itemName, String amount, int quantity, double price, Date saleEnd) {
		this(itemID, itemName, amount, quantity, price);
		this.setSaleEnd(saleEnd);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getSaleEnd() {
		return saleEnd;
	}

	public void setSaleEnd(Date saleEnd) {
		this.saleEnd = saleEnd;
	}

}
