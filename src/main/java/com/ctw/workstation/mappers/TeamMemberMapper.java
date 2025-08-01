package com.ctw.workstation.mappers;

import com.ctw.workstation.dto.TeamDTO;
import com.ctw.workstation.dto.TeamMemberDTO;
import com.ctw.workstation.entity.TeamMember;

public class TeamMemberMapper {
    public static TeamMember toEntity(TeamMemberDTO teamMemberDTO){
        TeamMember teamMember = new TeamMember();
        teamMember.setName(teamMemberDTO.getName());
        teamMember.setCtw_id(teamMemberDTO.getCtw_id());
        teamMember.setTeamId(teamMemberDTO.getTeamId());
        return teamMember;
    }

    public static TeamMemberDTO toDTO(TeamMember teamMember){
        TeamMemberDTO teamMemberDTO = new TeamMemberDTO();
        teamMemberDTO.setId(teamMember.getId());
        teamMemberDTO.setName(teamMember.getName());
        teamMemberDTO.setCtw_id(teamMember.getCtw_id());
        teamMemberDTO.setTeamId(teamMember.getTeamId());
        teamMemberDTO.setTeam(TeamMapper.toDTO(teamMember.getTeam()));
        teamMemberDTO.setCreatedAt(teamMember.getCreatedAt());
        teamMemberDTO.setModifiedAt(teamMember.getModifiedAt());
        return teamMemberDTO;
    }
}
