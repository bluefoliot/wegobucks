package org.wegobucks.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.wegobucks.api.DrinkResponseBean;
import org.wegobucks.dao.DrinkDao;
import org.wegobucks.dao.DrinkSizeDao;
import org.wegobucks.model.Drink;
import org.wegobucks.service.DrinkService;

import io.dropwizard.hibernate.UnitOfWork;


/**
 * @author jonathankurniadi
 *
 * Resource for handling drink related request
 */
@Path("/drink")
@Produces(MediaType.APPLICATION_JSON)
public class DrinkResource {
	
	private DrinkService service;
	
	public DrinkResource(DrinkService service) {
		this.service = service;
	}
	
	@GET
	@Path("/all")
	@UnitOfWork
	public Response getDrinks() {
		return Response.ok(service.getAllDrinks()).build();
	}
	
	@GET
	@Path("/sizes")
	@UnitOfWork
	public Response getDrinkSizes() {
		return Response.ok(service.getAllSizes()).build();
	}
	
	
	@GET
	@Path("/types")
	@UnitOfWork
	public Response getDrinkTypes() {
		return Response.ok(service.getAllTypes()).build();
	}
	
}
