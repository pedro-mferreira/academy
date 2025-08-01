package com.ctw.workstation.resource;

import com.ctw.workstation.dto.TeamMemberDTO;
import com.ctw.workstation.service.TeamMemberService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/team-members")
public class TeamMemberResource {

    @Inject
    TeamMemberService teamMemberService;

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTeamMembers() {
        var dtos = teamMemberService.getAllTeamMembers();
        if (dtos.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeamMemberById(@PathParam("id") Long id) {
        var dto = teamMemberService.getTeamMemberById(id);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTeamMember(TeamMemberDTO dto) {
        var created = teamMemberService.createTeamMember(dto);
        if (created == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Team not found or persistence failed").build();
        }
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTeamMember(@PathParam("id") Long id, TeamMemberDTO dto) {
        var updated = teamMemberService.updateTeamMember(id, dto);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Team member not found with id " + id).build();
        }
        return Response.ok(updated).build();
    }
}
