package io.robusta.funko.ws;

import io.robusta.funko.entities.Universe;
import io.robusta.funko.service.UniverseService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by AELION on 05/04/2017.
 */
@Path("universes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/json")
public class UniverseWs {

    @EJB
    UniverseService service;

    @GET
    @Path("{id}")
    public Universe findById(@PathParam("id") int id) {

        return service.findById(id).orElseThrow(NotFoundException::new);

    }


}
