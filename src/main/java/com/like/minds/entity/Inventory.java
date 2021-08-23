package com.like.minds.entity;

import java.util.Map;
import java.util.Optional;

public class Inventory {
	private Map<Item, Integer> items;
	public Inventory(Map<Item, Integer> items) {
		this.items = items;
	}
	public void addItem(Item item, Integer quantity) {
		items.put(item,
				items.getOrDefault(item, 0) + quantity);
	}
	public void updateItem(Item item, Integer quantity) {
		items.put(item, items.get(item)-quantity);
	}
	public Optional<Item> getItem(String category, String brand) {
		Item searchItem = new Item(category, brand, 0);
		for(Map.Entry<Item, Integer> entry : items.entrySet()) {
			if(entry.getKey().equals(searchItem)) {
				return Optional.of(entry.getKey());
			}
		}
		return Optional.empty();
	}
	
	public int getQuantity(Item item) {
		return items.get(item);
	}
}
