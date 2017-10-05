package org.wegobucks.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.wegobucks.model.Drink;

import io.dropwizard.hibernate.AbstractDAO;

public class DrinkDao extends AbstractDAO<Drink> {

	public DrinkDao(SessionFactory sessionFactory) {
		super(sessionFactory);
		
	}

	public Drink findById(int id) {
		return get(id);
	}
	
	public int create(Drink drink) {
		return persist(drink).getId();
	}
	
	public List<Drink> findAll() {
		Criteria criteria = criteria();
		return list(criteria);
	}
	
	public Drink find(String drinkSlug) {
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("slug", drinkSlug));
		return uniqueResult(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getDrinkTypes() {
		Criteria criteria = criteria();
		criteria.setProjection(Projections.distinct(Projections.property("type")));
		return criteria.list();
	}
	
}
