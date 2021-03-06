package org.wegobucks.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.dhatim.dropwizard.jwt.cookie.authentication.DefaultJwtCookiePrincipal;

/**
 * @author jonathankurniadi
 * 
 * Resource for handling authentication
 */
@Path("/auth")
public class AuthResource {

	/**
	 * @param requestContext
	 * @param name
	 * @return a token stored in cookie, for simplicity sake
	 */
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DefaultJwtCookiePrincipal login(@Context ContainerRequestContext requestContext, String name){
	    DefaultJwtCookiePrincipal principal = new DefaultJwtCookiePrincipal(name);
	    List<String> roles = new ArrayList<String>();
	    roles.add("admin");
	    principal.setRoles(roles);
	    principal.addInContext(requestContext);
	    return principal;
	}
}
