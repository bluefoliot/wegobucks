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
 * Resource for handling drink types related request
 */
@Path("/drink-types")
@Produces(MediaType.APPLICATION_JSON)
public class DrinkTypeResource {
	
	private DrinkService service;
	
	public DrinkTypeResource(DrinkService service) {
		this.service = service;
	}
	
	@GET
	@UnitOfWork
	public Response getDrinkTypes() {
		return Response.ok(service.getAllTypes()).build();
	}

}
