package org.wegobucks.resource;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.dhatim.dropwizard.jwt.cookie.authentication.DefaultJwtCookiePrincipal;

@Path("/auth")
public class AuthResource {

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
