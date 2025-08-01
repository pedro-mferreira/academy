package com.ctw.workstation.repository;

import com.ctw.workstation.entity.Booking;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookingRepository implements PanacheRepository<Booking> {
    public Booking findById(String id) {
        return find("id", id).firstResult();
    }
}
