package com.ctw.workstation.resource;

import com.ctw.workstation.dto.RackDTO;
import com.ctw.workstation.service.RackService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/racks")
public class RackResource {

    @Inject
    RackService rackService;

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRacks() {
        var dtos = rackService.getAllRacks();
        if (dtos.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRackById(@PathParam("id") Long id) {
        var dto = rackService.getRackById(id);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRack(RackDTO rackDTO) {
        var dto = rackService.createRack(rackDTO);
        if (dto == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Rack not persisted").build();
        }
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRack(@PathParam("id") Long id, RackDTO rackDTO) {
        var dto = rackService.updateRack(id, rackDTO);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rack not found with id " + id).build();
        }
        return Response.ok(dto).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRack(@PathParam("id") Long id) {
        RackDTO deleted = rackService.deleteRack(id);
        if (deleted != null) {
            return Response.status(Response.Status.OK).entity(deleted).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rack not found with id " + id)
                    .build();
        }
    }

}
