package com.ctw.workstation.entity;




import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_RACK")
public class Rack extends PanacheEntityBase {
    public static final String GET_ALL = "Rack.getAll";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rackIdGenerator")
    @SequenceGenerator(name = "rackIdGenerator", sequenceName = "SEQ_RACK_ID")
    public Long id;

    @Column(name = "SERIAL_NUMBER", length = 20, nullable = false)
    public String serialNumber;

    @Transient
    public Integer age;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ASSEMBLED_AT")
    public LocalDateTime assembledAt;

    @Enumerated(EnumType.STRING)
    @ColumnTransformer(write="?::rack_status_enum")
    @Column(name = "STATUS")
    public Status status;

    @Column(name = "TEAM_ID", nullable = false)
    public Long teamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID", updatable = false, insertable = false, nullable = false)
    public Team team;


    @OneToMany(mappedBy = "rackId", fetch = FetchType.LAZY)
    private List<RackAsset> rackAssets;


    @OneToMany(mappedBy = "rackId", fetch = FetchType.LAZY)
    private List<Booking> bookings;

    @CreationTimestamp
    @Column(name = "CREATED_AT")
    public LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "MODIFIED_AT")
    public LocalDateTime modifiedAt;

    @Column(name = "DEFAULT_LOCATION", nullable = false)
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<RackAsset> getRackAssets() {
        return rackAssets;
    }

    public void setRackAssets(List<RackAsset> rackAssets) {
        this.rackAssets = rackAssets;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
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
}
