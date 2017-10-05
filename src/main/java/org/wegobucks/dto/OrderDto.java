package org.wegobucks.dto;

public class OrderDto {
	
	private String drink;
	private String size;
	private String isHot;
	
	public String getDrink() {
		return drink;
	}
	public void setDrink(String drink) {
		this.drink = drink;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String isHot() {
		return isHot;
	}
	public void setHot(String isHot) {
		this.isHot = isHot;
	}
}
