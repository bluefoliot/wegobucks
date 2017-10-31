package org.wegobucks.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.wegobucks.service.DrinkService;

import io.dropwizard.hibernate.UnitOfWork;


/**
 * @author jonathankurniadi
 *
 * Resource for handling drink sizes related request
 */
@Path("/drink-sizes")
@Produces(MediaType.APPLICATION_JSON)
public class DrinkSizeResource {
	
	private DrinkService service;
	
	public DrinkSizeResource(DrinkService service) {
		this.service = service;
	}
	
	@GET
	@UnitOfWork
	public Response getDrinkSizes() {
		return Response.ok(service.getAllSizes()).build();
	}

}
