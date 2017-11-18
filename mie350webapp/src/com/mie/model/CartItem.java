package com.mie.model;

public class CartItem extends Item {
	int quantity;

	public CartItem(String item_name, int quantity) {
		this.setItemName(item_name);
		this.setQuantity(quantity);
	}
	
	public CartItem(int itemID, int quantity) {
		this.setItemID(itemID);
		this.setQuantity(quantity);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
