package io.github.carlosthe19916.core;

import io.github.carlosthe19916.core.representations.idm.UserRepresentation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("profile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ProfileResource {

    @GET
    UserRepresentation getProfile();

}
