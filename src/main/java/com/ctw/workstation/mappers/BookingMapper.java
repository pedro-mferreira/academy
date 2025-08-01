package com.ctw.workstation.mappers;

import com.ctw.workstation.dto.BookingDTO;
import com.ctw.workstation.entity.Booking;

public class BookingMapper {
    public static Booking toEntity(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setRackId(bookingDTO.getRackId());
        booking.setBookTo(bookingDTO.getBookTo());
        booking.setBookFrom(bookingDTO.getBookFrom());
        booking.setRequesterId(bookingDTO.getRequesterId());
        return booking;
    }

    public static BookingDTO toDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setRackId(booking.getRackId());
        bookingDTO.setBookTo(booking.getBookTo());
        bookingDTO.setBookFrom(booking.getBookFrom());
        bookingDTO.setRequesterId(booking.getRequesterId());
        bookingDTO.setRack(RackMapper.toDTO(booking.getRack()));
        bookingDTO.setTeamMember(TeamMemberMapper.toDTO(booking.getTeamMember()));
        return bookingDTO;
    }
}
