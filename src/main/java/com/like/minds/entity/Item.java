package com.like.minds.entity;

public class Item {
	private String category;
	private String brand;
	private int price;
	
	public Item(String category, String brand, int price) {
		this.category = category;
		this.brand = brand;
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public String getBrand() {
		return brand;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
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
		Item other = (Item) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Item [category=" + category + ", brand=" + brand + ", price=" + price + "]";
	}	
	
}
