package org.wegobucks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="drink_size")
public class DrinkSize {
	
	public DrinkSize() {
		
	}
	
	public DrinkSize(String name, String slug, int sizeOrder) {
		this.name = name;
		this.slug = slug;
		this.sizeOrder = sizeOrder;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true, nullable=false)
	private String name;
	
	@Column(unique=true, nullable=false)
	private String slug;
	
	@Column(unique=true, nullable=false, name="size_order")
	private int sizeOrder;
	
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
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public int getSizeOrder() {
		return sizeOrder;
	}
	public void setSizeOrder(int sizeOrder) {
		this.sizeOrder = sizeOrder;
	}
	
}
