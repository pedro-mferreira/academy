package com.ctw.workstation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "T_BOOKING")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingIdGenerator")
    @SequenceGenerator(name = "bookingIdGenerator", sequenceName = "SEQ_BOOKING_ID")
    private Long id;

    @Column(name = "REQUESTER_ID", nullable = false)
    public Long requesterId;

    @Column(name = "RACK_ID", nullable = false)
    public Long rackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUESTER_ID", updatable = false, insertable = false, nullable = false)
    public TeamMember teamMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RACK_ID", updatable = false, insertable = false, nullable = false)
    public Rack rack;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BOOK_FROM")
    public LocalDateTime bookFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BOOK_TO")
    public LocalDateTime bookTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }


    public TeamMember getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(TeamMember teamMember) {
        this.teamMember = teamMember;
    }

    public LocalDateTime getBookFrom() {
        return bookFrom;
    }

    public void setBookFrom(LocalDateTime bookFrom) {
        this.bookFrom = bookFrom;
    }

    public LocalDateTime getBookTo() {
        return bookTo;
    }

    public void setBookTo(LocalDateTime bookTo) {
        this.bookTo = bookTo;
    }

    public Long getRackId() {
        return rackId;
    }

    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }
}
