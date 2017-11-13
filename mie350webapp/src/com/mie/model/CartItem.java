package com.mie.model;

public class CartItem extends Item {
	int quantity;

	public CartItem(String item_name, int quantity) {
		// TODO Auto-generated constructor stub
		this.setItemName(item_name);
		this.setQuantity(quantity);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
