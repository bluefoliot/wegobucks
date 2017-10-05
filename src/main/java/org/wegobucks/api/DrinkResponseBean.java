package org.wegobucks.api;

import java.util.ArrayList;
import java.util.List;

public class DrinkResponseBean {
	
	private String name;
	private String slug;
	private List<PriceBean> priceList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public List<PriceBean> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<PriceBean> priceList) {
		this.priceList = priceList;
	}
	public void appendPriceList(PriceBean price) {
		if(this.priceList==null) {
			this.priceList = new ArrayList<PriceBean>();
		}
		this.priceList.add(price);
	}
	
	/*private int id;
	private String name;
	private String size;
	private double price;
	private String slug;
	private String sizeSlug;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getSizeSlug() {
		return sizeSlug;
	}
	public void setSizeSlug(String sizeSlug) {
		this.sizeSlug = sizeSlug;
	}*/

}
