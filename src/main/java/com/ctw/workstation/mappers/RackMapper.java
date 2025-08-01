package com.ctw.workstation.mappers;

import com.ctw.workstation.dto.RackDTO;
import com.ctw.workstation.entity.Rack;
import com.ctw.workstation.entity.Status;

public class RackMapper {
    public static RackDTO toDTO(Rack rack) {
        RackDTO dto = new RackDTO();
        dto.setId(rack.getId());
        dto.setSerialNumber(rack.getSerialNumber());
        dto.setAssembledAt(rack.getAssembledAt());
        dto.setCreatedAt(rack.getCreatedAt());
        dto.setDefaultLocation(rack.getDefaultLocation());
        dto.setTeamId(rack.getTeamId());
        dto.setStatus(rack.getStatus());
        dto.setTeam(TeamMapper.toDTO(rack.getTeam()));
        return dto;
    }

    public static Rack toEntity(RackDTO dto) {
        Rack rack = new Rack();
        rack.setId(dto.getId());
        rack.setSerialNumber(dto.getSerialNumber());
        rack.setAssembledAt(dto.getAssembledAt());
        rack.setCreatedAt(dto.getCreatedAt());
        rack.setDefaultLocation(dto.getDefaultLocation());
        rack.setTeamId(dto.getTeamId());
        rack.setStatus(dto.getStatus());
        //rack.setTeam(TeamMapper.toEntity(dto.getTeam()));
        return rack;
    }
}
