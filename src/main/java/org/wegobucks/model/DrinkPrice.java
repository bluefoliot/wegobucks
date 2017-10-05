package org.wegobucks.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="drink_price", uniqueConstraints=@UniqueConstraint(columnNames={"drink_id", "size_id"}))
public class DrinkPrice {
	
	public DrinkPrice() {
		super();
	}
	
	public DrinkPrice(Drink drink, DrinkSize size, Double price) {
		super();
		this.drink = drink;
		this.size = size;
		this.price = price;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(nullable=false)
	private Drink drink;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(nullable=false)
	private DrinkSize size;
	
	@Column(precision=2)
	private Double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public DrinkSize getSize() {
		return size;
	}

	public void setSize(DrinkSize size) {
		this.size = size;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
