package org.wegobucks.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.wegobucks.model.DrinkSize;

import io.dropwizard.hibernate.AbstractDAO;

public class DrinkSizeDao extends AbstractDAO<DrinkSize> {

	public DrinkSizeDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public DrinkSize findById(String id) {
		return get(id);
	}
	
	public int create(DrinkSize drinkSize) {
		return persist(drinkSize).getId();
	}
	
	public List<DrinkSize> findAll() {
		Criteria criteria = criteria();
		criteria.addOrder(Order.asc("sizeOrder"));
		return list(criteria);
	}
	
	public DrinkSize findBySlug(String slug) {
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("slug", slug));
		return uniqueResult(criteria);
	}

}
