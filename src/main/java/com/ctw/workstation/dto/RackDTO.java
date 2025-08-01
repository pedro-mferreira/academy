package com.ctw.workstation.dto;

import com.ctw.workstation.entity.Status;
import com.ctw.workstation.entity.Team;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class RackDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long id;
    public String serialNumber;
    public Integer age;
    public LocalDateTime assembledAt;
    public Status status;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Long teamId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public TeamDTO team;
    public LocalDateTime createdAt;
    public LocalDateTime modifiedAt;
    public String defaultLocation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getAssembledAt() {
        return assembledAt;
    }

    public void setAssembledAt(LocalDateTime assembledAt) {
        this.assembledAt = assembledAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }
}
