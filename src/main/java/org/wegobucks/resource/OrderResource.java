package org.wegobucks.resource;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.wegobucks.api.OrderRequestBean;
import org.wegobucks.api.OrderResponseBean;
import org.wegobucks.api.SalesResponseBean;
import org.wegobucks.service.OrderService;

import io.dropwizard.hibernate.UnitOfWork;

/**
 * @author jonathankurniadi
 *
 * Resource for handling order and sales related request
 */
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
	
	private OrderService service;
	
	public OrderResource(OrderService service) {
		this.service = service;
	}
	

	@POST
	@UnitOfWork
	public Response addOrder(OrderRequestBean bean) {
		OrderResponseBean response = service.createOrder(bean);
		if(response==null) {
			return Response.status(422).build();
		} else{
			return Response.ok(response).build();
		}
	}
	
	@GET
	@UnitOfWork
	@RolesAllowed("admin")
	public Response getSales(@QueryParam("type") String type, @QueryParam("size") String size) {
		SalesResponseBean response = service.getSales(type, size);
		return Response.ok(response).build();
	}
	
}
