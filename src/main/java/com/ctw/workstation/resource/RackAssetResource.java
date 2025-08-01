package com.ctw.workstation.resource;

import com.ctw.workstation.dto.RackAssetDTO;
import com.ctw.workstation.service.RackAssetService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/rack-assets")
public class RackAssetResource {

    @Inject
    RackAssetService rackAssetService;

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRackAssets() {
        var dtos = rackAssetService.getAllRackAssets();
        if (dtos.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRackAssetById(@PathParam("id") Long id) {
        var dto = rackAssetService.getRackAssetById(id);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRackAsset(RackAssetDTO rackAssetDTO) {
        var dto = rackAssetService.createRackAsset(rackAssetDTO);
        if (dto == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Rack asset not persisted").build();
        }
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRackAsset(@PathParam("id") Long id, RackAssetDTO rackAssetDTO) {
        var dto = rackAssetService.updateRackAsset(id, rackAssetDTO);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rack asset not found with id " + id).build();
        }
        return Response.ok(dto).build();
    }
}
