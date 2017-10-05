package org.wegobucks.api;

public class PriceBean {

	private String size;
	private String sizeSlug;
	private Double price;
	private int order;
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getSizeSlug() {
		return sizeSlug;
	}
	public void setSizeSlug(String sizeSlug) {
		this.sizeSlug = sizeSlug;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
}
