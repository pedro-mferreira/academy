package com.ctw.workstation.resource;

import com.ctw.workstation.dto.TeamDTO;
import com.ctw.workstation.service.TeamService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/teams")
public class TeamResource {

    @Inject
    TeamService teamService;

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTeams() {
        var dtos = teamService.getAllTeams();
        if (dtos.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        var dto = teamService.getTeamById(id);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTeam(TeamDTO dto) {
        var created = teamService.createTeam(dto);
        if (created == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Team not persisted").build();
        }
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTeam(@PathParam("id") Long id, TeamDTO dto) {
        var updated = teamService.updateTeam(id, dto);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Team not found with id " + id).build();
        }
        return Response.ok(updated).build();
    }
}
