package org.wegobucks.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wegobucks.api.DrinkResponseBean;
import org.wegobucks.api.DrinkSizeResponseBean;
import org.wegobucks.api.DrinkTypeResponseBean;
import org.wegobucks.api.PriceBean;
import org.wegobucks.dao.DrinkDao;
import org.wegobucks.dao.DrinkPriceDao;
import org.wegobucks.dao.DrinkSizeDao;
import org.wegobucks.model.Drink;
import org.wegobucks.model.DrinkPrice;
import org.wegobucks.model.DrinkSize;


/**
 * @author jonathankurniadi
 *
 * A singleton service, contains business logic for drink related data.
 */
public class DrinkService {
	
	private static DrinkService instance;
	private DrinkPriceDao drinkPriceDao;
	private DrinkSizeDao drinkSizeDao;
	private DrinkDao drinkDao;
	
	protected DrinkService(DrinkPriceDao drinkPriceDao, DrinkSizeDao drinkSizeDao, DrinkDao drinkDao) {
		this.drinkPriceDao = drinkPriceDao;
		this.drinkSizeDao = drinkSizeDao;
		this.drinkDao = drinkDao;
	}
	
	public static DrinkService getInstance(DrinkPriceDao drinkPriceDao, DrinkSizeDao drinkSizeDao, DrinkDao drinkDao) {
		if (instance==null) {
			instance = new DrinkService(drinkPriceDao, drinkSizeDao, drinkDao);
		}
		return instance;
	}
	
	public List<DrinkResponseBean> getAllDrinks() {
		List<DrinkPrice> priceList = drinkPriceDao.findAll();
		List<DrinkResponseBean> beans = new ArrayList<DrinkResponseBean>();
		Map<Integer, DrinkResponseBean> drinkMap = new HashMap<Integer, DrinkResponseBean>();
		for(DrinkPrice price: priceList) {
			DrinkResponseBean bean;
			
			//add price by size for each drink types
			if(!drinkMap.containsKey(price.getDrink().getId())) {
				bean = this.toDrinkBean(price.getDrink());
				drinkMap.put(price.getDrink().getId(), bean);
				beans.add(bean);
			} else {
				bean = drinkMap.get(price.getDrink().getId());
			}
			PriceBean priceBean = this.toPriceBean(price);
			bean.appendPriceList(priceBean);
		}
		return beans;
	}
	
	private DrinkResponseBean toDrinkBean(Drink drink) {
		DrinkResponseBean bean = new DrinkResponseBean();
		bean.setName(drink.getName());
		bean.setSlug(drink.getSlug());
		return bean;
	}
	
	private PriceBean toPriceBean(DrinkPrice price) {
		PriceBean bean = new PriceBean();
		bean.setSize(price.getSize().getName());
		bean.setSizeSlug(price.getSize().getSlug());
		bean.setPrice(price.getPrice());
		bean.setOrder(price.getSize().getSizeOrder());
		return bean;
	}
	
	public List<DrinkSizeResponseBean> getAllSizes() {
		List<DrinkSizeResponseBean> beans = new ArrayList<DrinkSizeResponseBean>();
		List<DrinkSize> sizes = drinkSizeDao.findAll();
		for(DrinkSize size: sizes) {
			DrinkSizeResponseBean bean = new DrinkSizeResponseBean();
			bean.setName(size.getName());
			bean.setOrder(size.getSizeOrder());
			bean.setSlug(size.getSlug());
			beans.add(bean);
		}
		return beans;
	}
	
	public List<DrinkTypeResponseBean> getAllTypes() {
		List<DrinkTypeResponseBean> beans = new ArrayList<DrinkTypeResponseBean>();
		List<String> types = drinkDao.getDrinkTypes();
		for(String type: types) {
			DrinkTypeResponseBean bean = new DrinkTypeResponseBean();
			bean.setType(type);
			beans.add(bean);
		}
		return beans;
	}
	
	
}
