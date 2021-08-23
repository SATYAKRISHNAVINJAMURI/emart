package com.like.minds.entity;

import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Inventory {
	final static Logger log = LoggerFactory.getLogger(Inventory.class);
	private Map<Item, Integer> items;
	public Inventory(Map<Item, Integer> items) {
		this.items = items;
	}
	public void addItem(Item item, Integer quantity) {
		log.info("addItem : {}, {}", item, quantity);
		log.info("items: {}", items);
		items.put(item,
				items.getOrDefault(item, 0) + quantity);
	}
	public void updateItem(Item item, Integer quantity) {
		log.info("updateItem : {}, {}", item, quantity);
		items.put(item, items.get(item)-quantity);
	}
	public Optional<Item> getItem(String category, String brand) {
		log.info("getItem : {}, {}", category, brand);
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
	@Override
	public String toString() {
		return "Inventory [items=" + items + "]";
	}
}

