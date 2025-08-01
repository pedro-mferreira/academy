package com.ctw.workstation.dto;

import com.ctw.workstation.entity.Rack;
import com.ctw.workstation.entity.TeamMember;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
public class BookingDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Long requesterId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Long rackId;
    public LocalDateTime bookFrom;
    public LocalDateTime bookTo;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public TeamMemberDTO teamMember;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public RackDTO rack;

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

    public Long getRackId() {
        return rackId;
    }

    public void setRackId(Long rackId) {
        this.rackId = rackId;
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

    public TeamMemberDTO getTeamMember() {
        return teamMember;
    }
    public void setTeamMember(TeamMemberDTO teamMemberDTO) {
        this.teamMember = teamMemberDTO;
    }
    public RackDTO getRack() {
        return rack;
    }
    public void setRack(RackDTO rackDTO) {
        this.rack = rackDTO;
    }

    public BookingDTO(Long requesterId, Long rackId, LocalDateTime bookFrom, LocalDateTime bookTo) {
        this.requesterId = requesterId;
        this.rackId = rackId;
        this.bookFrom = bookFrom;
        this.bookTo = bookTo;
    }

    public BookingDTO() {
    }
}
