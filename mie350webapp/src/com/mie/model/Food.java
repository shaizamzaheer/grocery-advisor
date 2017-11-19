package com.mie.model;

public class Food {
	private String foodName;

	public Food(String foodName) {
		setFoodName(foodName);
	}
	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
}
