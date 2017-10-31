package org.wegobucks.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.wegobucks.model.Order;

import io.dropwizard.hibernate.AbstractDAO;

public class OrderDao extends AbstractDAO<Order> {

	public OrderDao(SessionFactory sessionFactory) {
		super(sessionFactory);
		
	}
	
	public Order findById(int id) {
		return get(id);
	}
	
	public int create(Order order) {
		return persist(order).getId();
	}
	
	public List<Order> findAll() {
		Criteria criteria = criteria();
		return list(criteria);
	}
	
	public List<Order> find(String type, String size) {
		Criteria criteria = criteria();
		if(StringUtils.isNotBlank(type)) {
			criteria.add(Restrictions.eq("type", type));
		}
		if(StringUtils.isNotBlank(size)) {
			criteria.add(Restrictions.eq("sizeSlug", size));
		}
		return list(criteria);
	}

}
