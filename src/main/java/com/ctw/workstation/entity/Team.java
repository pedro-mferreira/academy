package com.ctw.workstation.entity;



import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_TEAM")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamIdGenerator")
    @SequenceGenerator(name = "teamIdGenerator", sequenceName = "SEQ_TEAM_ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Rack> racks;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<TeamMember> teamMembers;

    @CreationTimestamp
    @Column(name = "CREATED_AT")
    public LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "MODIFIED_AT")
    public LocalDateTime modifiedAt;

    @Column(name= "DEFAULT_LOCATION")
    private String defaultLocation;

    @Column(name= "PRODUCT")
    private String product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*
    public List<Rack> getRacks() {
        return racks;
    }

 */

    public void setRacks(List<Rack> racks) {
        this.racks = racks;
    }

    /*
    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }


     */
    public void setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public Team(String name, LocalDateTime createdAt, LocalDateTime modifiedAt, String defaultLocation, String product) {
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.defaultLocation = defaultLocation;
        this.product = product;
    }

    public Team() {
    }
}

