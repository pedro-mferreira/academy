package com.ctw.workstation.repository;


import com.ctw.workstation.entity.TeamMember;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TeamMemberRepository implements PanacheRepository<TeamMember> {
    public TeamMember findByName(String name) {
        return find("name", name).firstResult();
    }
}
