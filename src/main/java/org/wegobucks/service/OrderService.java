package org.wegobucks.service;

import java.util.ArrayList;
import java.util.List;

import org.wegobucks.api.OrderRequestBean;
import org.wegobucks.api.OrderResponseBean;
import org.wegobucks.api.SalesResponseBean;
import org.wegobucks.dao.DrinkDao;
import org.wegobucks.dao.DrinkPriceDao;
import org.wegobucks.dao.OrderDao;
import org.wegobucks.model.Drink;
import org.wegobucks.model.DrinkPrice;
import org.wegobucks.model.Order;

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
	
	public SalesResponseBean getAllSales() {
		SalesResponseBean salesBean = new SalesResponseBean();
		List<Order> orders = orderDao.findAll();
		List<OrderResponseBean> beans = new ArrayList<OrderResponseBean>();
		for(Order order: orders) {
			OrderResponseBean bean = this.buildResponseBean(order);
			beans.add(bean);
		}
		salesBean.setOrderResponseBean(beans);
		return salesBean;
	}
	
	public SalesResponseBean getSalesByType(String type) {
		SalesResponseBean salesBean = new SalesResponseBean();
		List<Order> orders = orderDao.findByType(type);
		List<OrderResponseBean> beans = new ArrayList<OrderResponseBean>();
		for(Order order: orders) {
			OrderResponseBean bean = this.buildResponseBean(order);
			beans.add(bean);
		}
		salesBean.setOrderResponseBean(beans);
		return salesBean;
	}
	
	public SalesResponseBean getSalesBySize(String size) {
		SalesResponseBean salesBean = new SalesResponseBean();
		List<Order> orders = orderDao.findBySize(size);
		List<OrderResponseBean> beans = new ArrayList<OrderResponseBean>();
		for(Order order: orders) {
			OrderResponseBean bean = this.buildResponseBean(order);
			beans.add(bean);
		}
		salesBean.setOrderResponseBean(beans);
		return salesBean;
	}
	
}
