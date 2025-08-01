package com.ctw.workstation.service;

import com.ctw.workstation.dto.BookingDTO;
import com.ctw.workstation.entity.Booking;
import com.ctw.workstation.entity.Rack;
import com.ctw.workstation.entity.TeamMember;
import com.ctw.workstation.mappers.BookingMapper;
import com.ctw.workstation.repository.BookingRepository;
import com.ctw.workstation.repository.RackRepository;
import com.ctw.workstation.repository.TeamMemberRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookingService {
    @PersistenceContext
    EntityManager em;

    @Inject
    BookingRepository bookingRepository;

    @Inject
    RackRepository rackRepository;

    @Inject
    TeamMemberRepository teamMemberRepository;

    @Inject
    Logger logger;

    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.listAll();

        if (bookings.isEmpty()) {
            logger.infov("No bookings found in database");
            return List.of();
        }

        List<BookingDTO> dtos = bookings.stream()
                .map(BookingMapper::toDTO)
                .collect(Collectors.toList());

        logger.infov("Returned {0} bookings", dtos.size());
        return dtos;
    }

    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id);

        if (booking == null) {
            logger.infov("Booking not found with id {0}", id);
            return null;
        }

        logger.infov("Returned booking with id {0}", id);
        return BookingMapper.toDTO(booking);
    }

    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = BookingMapper.toEntity(bookingDTO);
        Rack rack = rackRepository.findById(bookingDTO.getRackId());
        booking.setRack(rack);

        TeamMember teamMember = teamMemberRepository.findById(booking.getRequesterId());
        booking.setTeamMember(teamMember);

        if (rack == null || teamMember == null) {
            logger.warn("Invalid rack or team member in booking creation");
            return null;
        }

        bookingRepository.persist(booking);

        if (booking.getId() == null) {
            logger.warn("Booking not persisted: ID not generated");
            return null;
        }

        logger.infov("Booking created with id {0}", booking.getId());
        return BookingMapper.toDTO(booking);
    }

    @Transactional
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        Booking existingBooking = bookingRepository.findById(id);

        if (existingBooking == null) {
            logger.infov("Booking not found for update with id {0}", id);
            return null;
        }

        existingBooking.setRequesterId(bookingDTO.getRequesterId());
        existingBooking.setBookTo(bookingDTO.getBookTo());
        existingBooking.setBookFrom(bookingDTO.getBookFrom());
        existingBooking.setRackId(bookingDTO.getRackId());
        em.flush();
        em.refresh(existingBooking);


        logger.infov("Booking updated with id {0}", id);
        return BookingMapper.toDTO(existingBooking);
    }
}

