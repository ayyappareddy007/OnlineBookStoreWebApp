package com.obs.model;

public class CartModel extends BookModel {
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartModel() {
		super();
	}
	
}
