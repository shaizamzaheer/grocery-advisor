package com.mie.model;

// this class holds information about a particular Item - itemID, item name (includes brand), it's amount (e.g. 12 L, 300 g)
public class Item {
	int itemID;
	String item_name;
	String item_type;
	String amount; //String b/c "12 L" or "300 g"
	//String img_path; //in case we use images for specific items
	
	public Item() {
		//This is probably terrible practice! Need an implicit super() for CartItem constructors
	}
	
	public Item(int itemID, String item_name, String amount) {
		this.setItemID(itemID);
		this.setItemName(item_name);
		this.setAmount(amount);
	}
	
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getItemName() {
		return item_name;
	}
	public void setItemName(String item_name) {
		this.item_name = item_name;
	}
	public String getItemType() {
		return item_type;
	}
	public void setItemType(String item_type) {
		this.item_type = item_type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	
	
}
