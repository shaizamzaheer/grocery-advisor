package com.mie.model;

// this class extends the Item class, thus it holds all the information of a particular item, in addition to the quantity chosen by the user

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

	public CartItem(int itemID, String itemName, String amount, int quantity) {
		super(itemID, itemName, amount);
		this.setQuantity(quantity);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
