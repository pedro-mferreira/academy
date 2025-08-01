package com.ctw.workstation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "T_RACK_ASSET")
public class RackAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rackAssetIdGenerator")
    @SequenceGenerator(name = "rackAssetIdGenerator", sequenceName = "SEQ_RACK_ASSET_ID")
    private Long id;

    @Column(name = "ASSET_TAG", length = 20, nullable = false)
    private String assetTag;

    @Column(name = "RACK_ID", nullable = false)
    public Long rackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RACK_ID", updatable = false, insertable = false, nullable = false)
    private Rack rack;

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

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }
}
