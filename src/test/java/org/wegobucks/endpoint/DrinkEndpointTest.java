package org.wegobucks.endpoint;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import org.assertj.core.api.Assertions;
import org.junit.ClassRule;
import org.junit.Test;
import org.wegobucks.app.WegobucksApp;
import org.wegobucks.config.WegobucksConfig;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

public class DrinkEndpointTest {

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

}
