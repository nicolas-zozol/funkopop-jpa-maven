package io.robusta.funko.ws;

import io.robusta.funko.cdi.FavoritesBean;
import io.robusta.funko.entities.Universe;
import io.robusta.funko.service.UniverseService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by AELION on 05/04/2017.
 */
@Path("universes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/json")
public class UniverseWs {

    @EJB
    UniverseService service;

    @Inject
    FavoritesBean favoritesBean;

    @GET
    @Path("{id}")
    public Universe findById(@PathParam("id") int id) {
        return service.findById(id).orElseThrow(NotFoundException::new);

    }

    @GET
    public List<Universe> findById() {
        return service.findAll();

    }


}
