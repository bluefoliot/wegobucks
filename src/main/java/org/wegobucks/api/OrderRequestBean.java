package org.wegobucks.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequestBean {
	
	private String drink;
	private String size;
	private boolean isHot;
	
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
	public boolean getIsHot() {
		return isHot;
	}
	public void setIsHot(boolean isHot) {
		this.isHot = isHot;
	}
}
