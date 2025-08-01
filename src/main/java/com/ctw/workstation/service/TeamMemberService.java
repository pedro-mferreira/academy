package com.ctw.workstation.service;

import com.ctw.workstation.dto.TeamMemberDTO;
import com.ctw.workstation.entity.Team;
import com.ctw.workstation.entity.TeamMember;
import com.ctw.workstation.mappers.TeamMemberMapper;
import com.ctw.workstation.repository.TeamMemberRepository;
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
public class TeamMemberService {

    @Inject
    TeamMemberRepository teamMemberRepository;

    @Inject
    TeamRepository teamRepository;

    @Inject
    Logger logger;

    @PersistenceContext
    EntityManager em;

    public List<TeamMemberDTO> getAllTeamMembers() {
        List<TeamMember> members = teamMemberRepository.listAll();
        logger.infov("Returned {0} team members", members.size());
        return members.stream().map(TeamMemberMapper::toDTO).collect(Collectors.toList());
    }

    public TeamMemberDTO getTeamMemberById(Long id) {
        TeamMember member = teamMemberRepository.findById(id);
        if (member == null) {
            logger.infov("Team member not found with id {0}", id);
            return null;
        }
        logger.infov("Returned team member with id {0}", id);
        return TeamMemberMapper.toDTO(member);
    }

    @Transactional
    public TeamMemberDTO createTeamMember(TeamMemberDTO dto) {
        TeamMember member = TeamMemberMapper.toEntity(dto);

        Team team = teamRepository.findById(member.getTeamId());
        if (team == null) {
            logger.warnf("Team not found with id {0}", member.getTeamId());
            return null;
        }

        member.setTeam(team);
        teamMemberRepository.persist(member);
        em.flush();
        em.refresh(member);

        if (member.getId() == null) {
            logger.warn("Team member not persisted: ID not generated");
            return null;
        }

        logger.infov("Team member created with id {0}", member.getId());
        return TeamMemberMapper.toDTO(member);
    }

    @Transactional
    public TeamMemberDTO updateTeamMember(Long id, TeamMemberDTO dto) {
        TeamMember member = teamMemberRepository.findById(id);
        if (member == null) {
            logger.infov("Team member not found for update with id {0}", id);
            return null;
        }

        member.setName(dto.getName());
        member.setCtw_id(dto.getCtw_id());
        member.setTeamId(dto.getTeamId());
        member.setModifiedAt(LocalDateTime.now());

        if (dto.getTeamId() != null) {
            Team team = teamRepository.findById(dto.getTeamId());
            member.setTeam(team);
        }

        em.flush();
        em.refresh(member);

        logger.infov("Team member updated with id {0}", id);
        return TeamMemberMapper.toDTO(member);
    }
}
