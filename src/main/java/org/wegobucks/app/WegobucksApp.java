package org.wegobucks.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.context.internal.ManagedSessionContext;
import org.wegobucks.config.WegobucksConfig;
import org.wegobucks.dao.DrinkDao;
import org.wegobucks.dao.DrinkPriceDao;
import org.wegobucks.dao.DrinkSizeDao;
import org.wegobucks.dao.OrderDao;
import org.wegobucks.model.Drink;
import org.wegobucks.model.DrinkPrice;
import org.wegobucks.model.DrinkSize;
import org.wegobucks.model.Order;
import org.wegobucks.resource.DrinkResource;
import org.wegobucks.resource.OrderResource;
import org.wegobucks.service.DrinkService;
import org.wegobucks.service.OrderService;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class WegobucksApp extends Application<WegobucksConfig> {

	private final HibernateBundle<WegobucksConfig> hibernate = new HibernateBundle<WegobucksConfig>(Drink.class,
			DrinkSize.class, DrinkPrice.class, Order.class) {
		public DataSourceFactory getDataSourceFactory(WegobucksConfig configuration) {
			return configuration.getDataSourceFactory();
		}
	};

	public static void main(String[] args) throws Exception {
		new WegobucksApp().run(args);
	}

	@Override
	public void run(WegobucksConfig config, Environment env) throws Exception {
		this.initDrinkSize();
		this.initDrinks();
		this.initDrinkPrice();
		
		final DrinkDao drinkDao = new DrinkDao(hibernate.getSessionFactory());
		final DrinkSizeDao drinkSizeDao = new DrinkSizeDao(hibernate.getSessionFactory());
		final OrderDao orderDao = new OrderDao(hibernate.getSessionFactory());
		final DrinkPriceDao drinkPriceDao = new DrinkPriceDao(hibernate.getSessionFactory());
		
		final DrinkService drinkService = DrinkService.getInstance(drinkPriceDao, drinkSizeDao, drinkDao);
		final OrderService orderService = OrderService.getInstance(orderDao, drinkPriceDao);
		
		final DrinkResource drinkResource = new DrinkResource(drinkService);
		final OrderResource orderResource = new OrderResource(orderService);
		env.jersey().register(drinkResource);
		env.jersey().register(orderResource);
		
		env.jersey().setUrlPattern("/api/*");
	}

	@Override
	public void initialize(Bootstrap<WegobucksConfig> bootstrap) {
		bootstrap.addBundle(hibernate);
		bootstrap.addBundle(new ViewBundle<WegobucksConfig>());
		bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
	}

	private void initDrinkSize() {
		Session session = hibernate.getSessionFactory().openSession();
		ManagedSessionContext.bind(session);
		DrinkSizeDao drinkSizeDao = new DrinkSizeDao(session.getSessionFactory());
		List<DrinkSize> drinkSizes = drinkSizeDao.findAll();
		if(drinkSizes==null || drinkSizes.isEmpty()) {
			DrinkSize tall = new DrinkSize();
			tall.setName("Tall");
			tall.setSlug("tall");
			tall.setSizeOrder(0);

			DrinkSize grande = new DrinkSize();
			grande.setName("Grande");
			grande.setSlug("grande");
			grande.setSizeOrder(1);
			
			DrinkSize venti = new DrinkSize();
			venti.setName("Venti");
			venti.setSlug("venti");
			venti.setSizeOrder(2);
			
			drinkSizeDao.create(tall);
			drinkSizeDao.create(grande);
			drinkSizeDao.create(venti);
		}
		session.close();
	}
	
	private void initDrinks() {
		Session session = hibernate.getSessionFactory().openSession();
		ManagedSessionContext.bind(session);
		DrinkDao drinkDao = new DrinkDao(session.getSessionFactory());
		List<Drink> drinks = drinkDao.findAll();
		if(drinks==null || drinks.isEmpty()) {
			Drink drink = new Drink("Espresso", "coffee", "espresso");
			drinkDao.create(drink);
			
			drink = new Drink("Latte", "coffee", "latte");
			drinkDao.create(drink);
			
			drink = new Drink("Cappucino", "coffee", "cappucino");
			drinkDao.create(drink);
			
			drink = new Drink("Green Tea", "tea", "green-tea");
			drinkDao.create(drink);
			
			drink = new Drink("Hot Tea", "tea", "hot-tea");
			drinkDao.create(drink);
			
		}
		
		session.close();
	}
	
	private void initDrinkPrice() {
		Session session = hibernate.getSessionFactory().openSession();
		ManagedSessionContext.bind(session);
		DrinkDao drinkDao = new DrinkDao(session.getSessionFactory());
		DrinkSizeDao drinkSizeDao = new DrinkSizeDao(session.getSessionFactory());
		DrinkPriceDao drinkPriceDao = new DrinkPriceDao(session.getSessionFactory());
		
		List<DrinkPrice> drinkPrices = drinkPriceDao.findAll();
		
		if(drinkPrices==null || drinkPrices.isEmpty()) {
			DrinkSize tall = drinkSizeDao.findBySlug("tall");
			DrinkSize grande = drinkSizeDao.findBySlug("grande");
			DrinkSize venti = drinkSizeDao.findBySlug("venti");
			
			Drink espresso = drinkDao.find("espresso");
			Drink latte = drinkDao.find("latte");
			Drink cappucino = drinkDao.find("cappucino");
			Drink greenTea = drinkDao.find("green-tea");
			Drink hotTea = drinkDao.find("hot-tea");
			
			DrinkPrice price = new DrinkPrice(espresso, tall, 1.95);
			drinkPriceDao.create(price);
			
			price = new DrinkPrice(espresso, grande, 2.05);
			drinkPriceDao.create(price);
			
			price = new DrinkPrice(espresso, venti, 2.35);
			drinkPriceDao.create(price);
			
			price = new DrinkPrice(latte, tall, 3.4);
			drinkPriceDao.create(price);
			
			price = new DrinkPrice(latte, grande, 4.45);
			drinkPriceDao.create(price);
			
			price = new DrinkPrice(latte, venti, 4.65);
			drinkPriceDao.create(price);
			
			price = new DrinkPrice(cappucino, tall, 3.15);
			drinkPriceDao.create(price);
			
			price = new DrinkPrice(cappucino, grande, 3.75);
			drinkPriceDao.create(price);
			
			price = new DrinkPrice(cappucino, venti, 4.15);
			drinkPriceDao.create(price);
			
			price = new DrinkPrice(greenTea, tall, 3.45);
			drinkPriceDao.create(price);
			
			price = new DrinkPrice(greenTea, grande, 4.25);
			drinkPriceDao.create(price);
			
			price = new DrinkPrice(greenTea, venti, 4.45);
			drinkPriceDao.create(price);
			
			price = new DrinkPrice(hotTea, tall, null);
			drinkPriceDao.create(price);
			
			price = new DrinkPrice(hotTea, grande, 1.95);
			drinkPriceDao.create(price);
			
			price = new DrinkPrice(hotTea, venti, null);
			drinkPriceDao.create(price);
			
		}
		
	}
	
	
	
}
