package com.ctw.workstation.dto;

import com.ctw.workstation.entity.Team;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class TeamMemberDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Long teamId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public TeamDTO team;

    public String ctw_id;

    private String name;

    public LocalDateTime createdAt;

    public LocalDateTime modifiedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }


    public String getCtw_id() {
        return ctw_id;
    }

    public void setCtw_id(String ctw_id) {
        this.ctw_id = ctw_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public TeamMemberDTO(Long teamId, String ctw_id, String name) {
        this.teamId = teamId;
        this.ctw_id = ctw_id;
        this.name = name;
    }

    public TeamMemberDTO() {
    }
}
