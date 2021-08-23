package com.like.minds.entity;

import java.util.Map;

public class Cart {
	private Map<Item, Integer> items;
	public Cart(Map<Item, Integer> items) {
		this.items = items;
	}
	public Map<Item, Integer> getItems() {
		return items;
	}
	public String addItem(Item item, Integer quantity) {
		if(isItemPresent(item)) {
			return "Item already present in the cart";
		}else {
			items.put(item, quantity);
			return "Item added to cart successfully";
		}
	}
	public String updateItem(Item item, Integer quantity) {
		if(!isItemPresent(item)) {
			return "Item not present in the cart";
		}else {
			items.put(item, quantity);
			return "Item quantity updated successfully";
		}
	}
	public String removeItem(Item item) {
		if(!isItemPresent(item)) {
			return "Item not present in the cart";
		}else {
			items.remove(item);
			return "Item removed successfully";
		}
	}
	public boolean isItemPresent(Item item) {
		for(Map.Entry<Item, Integer> entry : items.entrySet()) {
			if(entry.getKey().equals(item)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public String toString() {
		return "Cart [items=" + items + "]";
	}
}
