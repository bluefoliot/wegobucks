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
 * Resource for handling drink related request
 */
@Path("/drinks")
@Produces(MediaType.APPLICATION_JSON)
public class DrinkResource {
	
	private DrinkService service;
	
	public DrinkResource(DrinkService service) {
		this.service = service;
	}
	
	@GET
	@UnitOfWork
	public Response getDrinks() {
		return Response.ok(service.getAllDrinks()).build();
	}
	
}
