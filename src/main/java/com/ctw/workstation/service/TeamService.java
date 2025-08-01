package com.ctw.workstation.service;

import com.ctw.workstation.dto.TeamDTO;
import com.ctw.workstation.entity.Team;
import com.ctw.workstation.mappers.TeamMapper;
import com.ctw.workstation.repository.TeamRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TeamService {

    @Inject
    TeamRepository teamRepository;

    @Inject
    Logger logger;

    @PersistenceContext
    EntityManager em;

    public List<TeamDTO> getAllTeams() {
        List<Team> teams = teamRepository.listAll();
        logger.infov("Returned {0} teams", teams.size());
        return teams.stream().map(TeamMapper::toDTO).collect(Collectors.toList());
    }

    public TeamDTO getTeamById(Long id) {
        Team team = teamRepository.findById(id);
        if (team == null) {
            logger.infov("Team not found with id {0}", id);
            return null;
        }
        logger.infov("Returned team with id {0}", id);
        return TeamMapper.toDTO(team);
    }

    @Transactional
    public TeamDTO createTeam(TeamDTO dto) {
        Team team = TeamMapper.toEntity(dto);
        teamRepository.persist(team);
        em.flush();
        em.refresh(team);

        if (team.getId() == null) {
            logger.warn("Team not persisted: ID not generated");
            return null;
        }

        logger.infov("Team created with id {0}", team.getId());
        return TeamMapper.toDTO(team);
    }

    @Transactional
    public TeamDTO updateTeam(Long id, TeamDTO dto) {
        Team existing = teamRepository.findById(id);
        if (existing == null) {
            logger.infov("Team not found for update with id {0}", id);
            return null;
        }

        existing.setName(dto.getName());
        existing.setDefaultLocation(dto.getDefaultLocation());
        existing.setProduct(dto.getProduct());
        existing.setModifiedAt(LocalDateTime.now());

        em.flush();
        em.refresh(existing);

        logger.infov("Team updated with id {0}", id);
        return TeamMapper.toDTO(existing);
    }
}
