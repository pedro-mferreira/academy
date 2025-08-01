package com.ctw.workstation.dto;

import com.ctw.workstation.entity.Rack;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

public class RackAssetDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String assetTag;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Long rackId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private RackDTO rack;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public Long getRackId() {
        return rackId;
    }

    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }

    public RackDTO getRack() {
        return rack;
    }

    public void setRack(RackDTO rack) {
        this.rack = rack;
    }
}
