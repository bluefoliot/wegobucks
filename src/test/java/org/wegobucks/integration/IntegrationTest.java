package org.wegobucks.integration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

import org.assertj.core.api.Assertions;
import org.junit.ClassRule;
import org.junit.Test;
import org.wegobucks.app.WegobucksApp;
import org.wegobucks.config.WegobucksConfig;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

public class IntegrationTest {

	@ClassRule
	public static final DropwizardAppRule<WegobucksConfig> rule = new DropwizardAppRule<WegobucksConfig>(
			WegobucksApp.class, ResourceHelpers.resourceFilePath("test.yaml"));

	@Test
	public void testGetAllDrinks() {
		Client client = new JerseyClientBuilder(rule.getEnvironment()).build("test drinks");
		String url = String.format("http://localhost:%d/api/drink/all", rule.getLocalPort());
		Response response = client.target(url).request().get();
		Assertions.assertThat(response.getStatus()).isEqualTo(200);
	}
	
	@Test
	public void testGetAllTypes() {
		Client client = new JerseyClientBuilder(rule.getEnvironment()).build("test types");
		String url = String.format("http://localhost:%d/api/drink/types", rule.getLocalPort());
		Response response = client.target(url).request().get();
		Assertions.assertThat(response.getStatus()).isEqualTo(200);
	}
	
	@Test
	public void testGetAllSizes() {
		Client client = new JerseyClientBuilder(rule.getEnvironment()).build("test sizes");
		String url = String.format("http://localhost:%d/api/drink/sizes", rule.getLocalPort());
		Response response = client.target(url).request().get();
		Assertions.assertThat(response.getStatus()).isEqualTo(200);
	}
	
	@Test
	public void testUnauthenticatedSales() {
		Client client = new JerseyClientBuilder(rule.getEnvironment()).build("test unauthenticated sales");
		Response response = client.target(
                String.format("http://localhost:%d/api/order/all", rule.getLocalPort()))
               .request()
               .get();
		Assertions.assertThat(response.getStatus()).isEqualTo(401);
	}
	
	@Test
	public void testAuthentication() {
		Client client = new JerseyClientBuilder(rule.getEnvironment()).build("test authentication");
		MultivaluedHashMap<String, String> loginForm = new MultivaluedHashMap<String, String>();
		loginForm.add("name", "name");
		
		Response response = client.target(
                String.format("http://localhost:%d/api/auth/login", rule.getLocalPort()))
               .request()
               .post(Entity.json(loginForm));
		
		Assertions.assertThat(response.getStatus()).isEqualTo(200);
	}
	
	@Test
	public void testAuthenticatedSales() {
		Client client = new JerseyClientBuilder(rule.getEnvironment()).build("test authenticated sales");
		MultivaluedHashMap<String, String> loginForm = new MultivaluedHashMap<String, String>();
		loginForm.add("name", "sales");
		
		Response response = client.target(
                String.format("http://localhost:%d/api/auth/login", rule.getLocalPort()))
               .request()
               .post(Entity.json(loginForm));
		
		response = client.target(
                String.format("http://localhost:%d/api/order/all", rule.getLocalPort()))
               .request()
               .cookie(response.getCookies().get("sessionToken"))
               .get();
		Assertions.assertThat(response.getStatus()).isEqualTo(200);
	}
	
	@Test
	public void testAuthenticatedSalesByType() {
		Client client = new JerseyClientBuilder(rule.getEnvironment()).build("test authenticated sales by type");
		MultivaluedHashMap<String, String> loginForm = new MultivaluedHashMap<String, String>();
		loginForm.add("name", "type");
		
		Response response = client.target(
                String.format("http://localhost:%d/api/auth/login", rule.getLocalPort()))
               .request()
               .post(Entity.json(loginForm));
		
		response = client.target(
                String.format("http://localhost:%d/api/order/type/coffee", rule.getLocalPort()))
               .request()
               .cookie(response.getCookies().get("sessionToken"))
               .get();
		Assertions.assertThat(response.getStatus()).isEqualTo(200);
	}
	
	@Test
	public void testAuthenticatedSalesBySize() {
		Client client = new JerseyClientBuilder(rule.getEnvironment()).build("test authenticated sales by size");
		MultivaluedHashMap<String, String> loginForm = new MultivaluedHashMap<String, String>();
		loginForm.add("name", "size");
		
		Response response = client.target(
                String.format("http://localhost:%d/api/auth/login", rule.getLocalPort()))
               .request()
               .post(Entity.json(loginForm));
		
		response = client.target(
                String.format("http://localhost:%d/api/order/size/tall", rule.getLocalPort()))
               .request()
               .cookie(response.getCookies().get("sessionToken"))
               .get();
		Assertions.assertThat(response.getStatus()).isEqualTo(200);
	}

}
