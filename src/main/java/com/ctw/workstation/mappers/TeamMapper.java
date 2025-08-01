package com.ctw.workstation.mappers;

import com.ctw.workstation.dto.RackDTO;
import com.ctw.workstation.dto.TeamDTO;
import com.ctw.workstation.entity.Rack;
import com.ctw.workstation.entity.Team;

public class TeamMapper {
    public static TeamDTO toDTO(Team team) {
        TeamDTO dto = new TeamDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setDefaultLocation(team.getDefaultLocation());
        dto.setProduct(team.getProduct());
        dto.setCreatedAt(team.getCreatedAt());
        dto.setModifiedAt(team.getModifiedAt());
        return dto;
    }

    public static Team toEntity(TeamDTO dto) {
        Team team = new Team();
        team.setName(dto.getName());
        team.setDefaultLocation(dto.getDefaultLocation());
        team.setProduct(dto.getProduct());
        team.setCreatedAt(dto.getCreatedAt());
        team.setModifiedAt(dto.getModifiedAt());
        return team;
    }
}
