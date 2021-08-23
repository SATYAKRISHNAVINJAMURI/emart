package com.like.minds.entity;

import java.util.HashMap;

public class User {
	private String userName;
	private int walletAmount;
	private Cart cart;
	public User(String userName, int walletAmount,
			Cart cart) {
		this.userName = userName;
		this.walletAmount = walletAmount;
		this.cart = cart;
	}
	public String getUserName() {
		return userName;
	}
	public int getWalletAmount() {
		return walletAmount;
	}
	public void setWalletAmount(int walletAmount) {
		this.walletAmount = walletAmount;
	}
	public String addItem(Item item , Integer quantity) {
		return cart.addItem(item, quantity);
	}
	public String updateItem(Item item, int quantity) {
		return cart.updateItem(item, quantity);
	}
	
	public String removeItem(Item item) {
		return cart.removeItem(item);
	}
	
	public Cart getCart() {
		return cart;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	public void clearCart() {
		this.cart = new Cart(new HashMap<>());
	}
}
