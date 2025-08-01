package com.ctw.workstation.resource;

import com.ctw.workstation.dto.BookingDTO;
import com.ctw.workstation.service.BookingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.logging.Logger;

@Path("/bookings")
public class BookingResource {

    @Inject
    BookingService bookingService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBookings() {
        var dtos = bookingService.getAllBookings();
        if (dtos.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookingById(@PathParam("id") Long id) {
        var dto = bookingService.getBookingById(id);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBooking(BookingDTO bookingDTO) {
        var dto = bookingService.createBooking(bookingDTO);
        if (dto == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid rack or team member or error persisting booking").build();
        }
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBooking(@PathParam("id") Long id, BookingDTO bookingDTO) {
        var dto = bookingService.updateBooking(id, bookingDTO);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Booking not found with id " + id)
                    .build();
        }
        return Response.ok(dto).build();
    }
}
