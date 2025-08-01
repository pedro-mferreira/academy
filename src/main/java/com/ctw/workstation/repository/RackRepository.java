package com.ctw.workstation.repository;

import com.ctw.workstation.entity.Rack;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RackRepository implements PanacheRepository<Rack> {
    public Rack findBySerialNumber(String serialNumber) {
        return find("serialNumber", serialNumber).firstResult();
    }
}
