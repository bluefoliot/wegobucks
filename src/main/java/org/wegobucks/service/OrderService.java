package org.wegobucks.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.wegobucks.api.OrderRequestBean;
import org.wegobucks.api.OrderResponseBean;
import org.wegobucks.api.SalesResponseBean;
import org.wegobucks.dao.DrinkPriceDao;
import org.wegobucks.dao.OrderDao;
import org.wegobucks.model.DrinkPrice;
import org.wegobucks.model.Order;

/**
 * @author jonathankurniadi
 *
 * A singleton service, contains business logic for order and sales related data.
 */
public class OrderService {

	private static OrderService instance;
	private OrderDao orderDao;
	private DrinkPriceDao drinkPriceDao;
	
	
	protected OrderService (OrderDao orderDao, DrinkPriceDao drinkPriceDao) {
		this.orderDao = orderDao;
		this.drinkPriceDao = drinkPriceDao;
	}
	
	public static OrderService getInstance(OrderDao orderDao, DrinkPriceDao drinkPriceDao) {
		if(instance==null) {
			instance = new OrderService(orderDao, drinkPriceDao);
		}
		return instance;
	}
	
	public OrderResponseBean createOrder(OrderRequestBean bean) {
		String drinkSlug = bean.getDrink();
		String sizeSlug = bean.getSize();
		
		DrinkPrice price = drinkPriceDao.find(drinkSlug, sizeSlug);
		if(price==null) {
			return null;
		}
		
		//copy the data instead of joining, to record the name and price at the time
		Order order = new Order();
		order.setName(price.getDrink().getName());
		order.setSize(price.getSize().getName());
		order.setSizeSlug(price.getSize().getSlug());
		order.setType(price.getDrink().getType());
		order.setAmount(price.getPrice());
		order.setHot(bean.getIsHot());
		
		orderDao.create(order);
		return this.buildResponseBean(order);
	}
	
	private OrderResponseBean buildResponseBean(Order order) {
		OrderResponseBean bean = new OrderResponseBean();
		bean.setId(order.getId());
		bean.setName(order.getName());
		bean.setSize(order.getSize());
		bean.setType(order.getType());
		bean.setAmount(order.getAmount());
		bean.setIsHot(order.isHot());
		return bean;
	}
	
	public SalesResponseBean getSales(String type, String size) {
		SalesResponseBean salesBean = new SalesResponseBean();
		List<Order> orders;
		
		if(StringUtils.isBlank(type) && StringUtils.isBlank(size)) {
			orders = orderDao.findAll();
		} else {
			orders = orderDao.find(type, size);
		}
		
		List<OrderResponseBean> beans = new ArrayList<OrderResponseBean>();
		for(Order order: orders) {
			OrderResponseBean bean = this.buildResponseBean(order);
			beans.add(bean);
		}
		salesBean.setOrderResponseBean(beans);
		return salesBean;
	}
	
}
