package com.mie.model;

public class CartItem extends Item implements Comparable<CartItem> {
	int quantity;
	boolean inCart;

	public CartItem(String item_name, int quantity) {
		this.setItemName(item_name);
		this.setQuantity(quantity);
	}
	
	public CartItem(int itemID, int quantity) {
		this.setItemID(itemID);
		this.setQuantity(quantity);
	}

	public CartItem(int itemID, String itemName, String amount, int quantity, boolean inCart) {
		super(itemID, itemName, amount);
		this.setQuantity(quantity);
		this.setInCart(inCart);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isInCart() {
		return inCart;
	}

	public void setInCart(boolean inCart) {
		this.inCart = inCart;
	}

	@Override
	public int compareTo(CartItem other) {

		if (this.inCart == true && other.inCart == false) 
			return -1;
		
		else if (this.inCart == false && other.inCart == true) 
			return 1;
		
		else {
			if (this.getItemName().compareTo(other.getItemName()) > 0)
				return 1;
			
			else if (this.getItemName().compareTo(other.getItemName()) < 0)
				return -1;
			
			else 
				return 0;
			
		}
	}
	
	
	
	
}
