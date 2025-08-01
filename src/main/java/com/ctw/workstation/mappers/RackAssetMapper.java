package com.ctw.workstation.mappers;

import com.ctw.workstation.dto.RackAssetDTO;
import com.ctw.workstation.entity.RackAsset;

public class RackAssetMapper {
    public static RackAssetDTO toDTO(RackAsset rackAsset) {
        RackAssetDTO rackAssetDTO = new RackAssetDTO();
        rackAssetDTO.setId(rackAsset.getId());
        rackAssetDTO.setAssetTag(rackAsset.getAssetTag());
        rackAssetDTO.setRack(RackMapper.toDTO(rackAsset.getRack()));
        rackAssetDTO.setRackId(rackAsset.getRackId());
        return rackAssetDTO;
    }

    public static RackAsset toEntity(RackAssetDTO rackAssetDTO) {
        RackAsset rackAsset = new RackAsset();
        rackAsset.setId(rackAssetDTO.getId());
        rackAsset.setAssetTag(rackAssetDTO.getAssetTag());
        rackAsset.setRack(RackMapper.toEntity(rackAssetDTO.getRack()));
        rackAsset.setRackId(rackAssetDTO.getRackId());
        return rackAsset;
    }
}
