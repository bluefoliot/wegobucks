package org.wegobucks.endpoint;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.Response;

import org.assertj.core.api.Assertions;
import org.eclipse.jetty.http.HttpHeader;
import org.junit.ClassRule;
import org.junit.Test;
import org.wegobucks.app.WegobucksApp;
import org.wegobucks.config.WegobucksConfig;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

public class OrderEndpointTest {
	
	@ClassRule
	public static final DropwizardAppRule<WegobucksConfig> rule = new DropwizardAppRule<WegobucksConfig>(
			WegobucksApp.class, ResourceHelpers.resourceFilePath("test.yaml"));
	
	@Test
	public void testSales() {
		ObjectMapper mapper = new ObjectMapper();
		Client client = new JerseyClientBuilder(rule.getEnvironment()).build("test sales");
		Response response = client.target(
                String.format("http://localhost:%d/api/order/all", rule.getLocalPort()))
               .request()
               .get();
		Assertions.assertThat(response.getStatus()).isEqualTo(401);
		
		MultivaluedHashMap<String, String> loginForm = new MultivaluedHashMap<>();
		loginForm.add("name", "name");
		
		response = client.target(
                String.format("http://localhost:%d/api/auth/login", rule.getLocalPort()))
               .request()
               .post(Entity.json(loginForm));
		
		Assertions.assertThat(response.getStatus()).isEqualTo(200);
		
		response = client.target(
                String.format("http://localhost:%d/api/order/all", rule.getLocalPort()))
               .request()
               .cookie(response.getCookies().get("sessionToken"))
               .get();
		Assertions.assertThat(response.getStatus()).isEqualTo(200);
		
		response = client.target(
                String.format("http://localhost:%d/api/order/type/coffee", rule.getLocalPort()))
               .request()
               .cookie(response.getCookies().get("sessionToken"))
               .get();
		Assertions.assertThat(response.getStatus()).isEqualTo(200);
		
		response = client.target(
                String.format("http://localhost:%d/api/order/size/tall", rule.getLocalPort()))
               .request()
               .cookie(response.getCookies().get("sessionToken"))
               .get();
		Assertions.assertThat(response.getStatus()).isEqualTo(200);
	}
}
