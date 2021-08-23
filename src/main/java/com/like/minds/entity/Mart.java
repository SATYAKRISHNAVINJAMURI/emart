package com.like.minds.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Mart {
	private Set<User> usersList;
	private Inventory inventory;
	private Set<Item> itemsList;

	public Mart(Set<User> usersList, Inventory inventory, Set<Item> itemsList) {
		this.usersList = usersList;
		this.inventory = inventory;
		this.itemsList = itemsList;
	}
	public String addUser(String userName, int amount) {
		usersList.add(new User(userName, amount, new Cart(new HashMap<>())));
		return "user added";
	}

	private Optional<User> getUser(String userName) {
		for (User user : usersList) {
			if (user.getUserName().equalsIgnoreCase(userName)) {
				return Optional.of(user);
			}
		}
		return Optional.empty();
	}

	public String addToCart(String userName, String itemCategory, String brand, int quantityRequired) {
		Optional<User> userOpt = getUser(userName);
		if (userOpt.isPresent()) {
			Optional<Item> itemOpt = inventory.getItem(itemCategory, brand);
			if (itemOpt.isPresent()) {
				int quantityPresent = inventory.getQuantity(itemOpt.get());
				if (quantityRequired <= quantityPresent) {
					return userOpt.get().addItem(itemOpt.get(), quantityRequired);
				} else {
					return "Inventory has less quantity of the item than required" + " quantityPresent: "
							+ quantityPresent;
				}
			} else {
				return "Requested Item is not present in the inventory";
			}
		} else {
			return "No user found with the give user name: " + userName;
		}
	}

	public String updateCart(String userName, String itemCategory, String brand, int quantityRequired) {
		Optional<User> userOpt = getUser(userName);
		if (userOpt.isPresent()) {
			Optional<Item> itemOpt = inventory.getItem(itemCategory, brand);
			if (itemOpt.isPresent()) {
				int quantityPresent = inventory.getQuantity(itemOpt.get());
				if (quantityRequired <= quantityPresent) {
					return userOpt.get().updateItem(itemOpt.get(), quantityRequired);
				} else {
					return "Inventory has less quantity of the item than required" + " quantityPresent: "
							+ quantityPresent;
				}
			} else {
				return "Requested Item is not present in the inventory";
			}
		} else {
			return "No user found with the give user name: " + userName;
		}
	}

	public String removeFromCart(String userName, String itemCategory, String brand) {
		Optional<User> userOpt = getUser(userName);
		if (userOpt.isPresent()) {
			Optional<Item> itemOpt = inventory.getItem(itemCategory, brand);
			if (itemOpt.isPresent()) {
				return userOpt.get().removeItem(itemOpt.get());
			} else {
				return "Requested Item is not present in the inventory";
			}
		} else {
			return "No user found with the give user name: " + userName;
		}
	}

	public String getCartDetails(String userName) {
		Optional<User> userOpt = getUser(userName);
		if (userOpt.isPresent()) {
			return userOpt.get().getCart().toString();
		} else {
			return "No user found with the give user name: " + userName;
		}
	}
	
	public String cartCheckout(String userName) {
		Optional<User> userOpt = getUser(userName);
		int totalPrice = 0;
		if (userOpt.isPresent()) {
			Cart cart = userOpt.get().getCart();
			int walletAmount = userOpt.get().getWalletAmount();
			for(Map.Entry<Item, Integer> entry : cart.getItems().entrySet()) {
				int requiredQuantity = entry.getValue();
				int availableQuantity = inventory.getQuantity(entry.getKey());
				if(requiredQuantity <= availableQuantity) {
					totalPrice += requiredQuantity * entry.getKey().getPrice();
				}else {
					return "Inventory doesn't have required quantity right now";
				}
			}
			if(totalPrice <= walletAmount) {
				userOpt.get().setWalletAmount(walletAmount-totalPrice);
				updateInventoryItems(cart.getItems());
				userOpt.get().clearCart();
				return "Cart checkedOut successfully";
			}else {
				return "User doesn't have enought wallet amount";
			}
		} else {
			return "No user found with the give user name: " + userName;
		}
	}
	
	public String createItem(String itemCategory, String brand, 
			int price) {
		itemsList.add(new Item(itemCategory, brand, price));
		return "Item created successfully";
	}
	private Optional<Item> getItem(String cat, String brand) {
		Item req = new Item(cat, brand, 0);
		for(Item item : itemsList) {
			if(item.equals(req)) {
				return Optional.of(item);
			}
		}
		return Optional.empty();
	}
	public String addInventory(String itemCategory, String brand, 
			int quantity) {
		Optional<Item> itemOpt = getItem(itemCategory, brand);
		if(itemOpt.isPresent()) {
			inventory.addItem(itemOpt.get(), quantity);
			return "Item added successfully to the inventory";
		}else {
			return "Please create item first";
		}
	}
	private void updateInventoryItems(Map<Item, Integer> items) {
		for(Map.Entry<Item, Integer> entry : items.entrySet()) {
			inventory.updateItem(entry.getKey(), entry.getValue());
		}
	}
}
