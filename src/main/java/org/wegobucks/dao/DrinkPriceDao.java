package org.wegobucks.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.wegobucks.model.DrinkPrice;

import io.dropwizard.hibernate.AbstractDAO;

public class DrinkPriceDao extends AbstractDAO<DrinkPrice> {

	public DrinkPriceDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public DrinkPrice findById(int id) {
		return get(id);
	}
	
	public int create(DrinkPrice drinkPrice) {
		return persist(drinkPrice).getId();
	}
	
	public List<DrinkPrice> findAll() {
		Criteria criteria = criteria();
		criteria.createAlias("size", "size");
		criteria.addOrder(Order.asc("size.sizeOrder"));
		return list(criteria);
	}
	
	public DrinkPrice find(String drinkSlug, String sizeSlug) {
		Criteria criteria = criteria();
		criteria.createAlias("drink", "drink");
		criteria.createAlias("size", "size");
		criteria.add(Restrictions.eq("drink.slug", drinkSlug));
		criteria.add(Restrictions.eq("size.slug", sizeSlug));
		return uniqueResult(criteria);
	}
	
}
