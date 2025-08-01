package com.ctw.workstation.service;

import com.ctw.workstation.dto.RackDTO;
import com.ctw.workstation.entity.Rack;
import com.ctw.workstation.mappers.RackMapper;
import com.ctw.workstation.repository.RackRepository;
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
public class RackService {

    @Inject
    RackRepository rackRepository;

    @Inject
    Logger logger;

    @PersistenceContext
    EntityManager em;

    public List<RackDTO> getAllRacks() {
        List<Rack> racks = rackRepository.listAll();
        logger.infov("Returned {0} racks", racks.size());
        return racks.stream().map(RackMapper::toDTO).collect(Collectors.toList());
    }

    public RackDTO getRackById(Long id) {
        Rack rack = rackRepository.findById(id);
        if (rack == null) {
            logger.infov("Rack not found with id {0}", id);
            return null;
        }
        logger.infov("Returned rack with id {0}", id);
        return RackMapper.toDTO(rack);
    }

    @Transactional
    public RackDTO createRack(RackDTO rackDTO) {
        Rack rack = RackMapper.toEntity(rackDTO);
        rack.setCreatedAt(LocalDateTime.now());
        rack.setModifiedAt(LocalDateTime.now());

        rackRepository.persist(rack);
        em.flush(); // garante que o ID foi gerado
        em.refresh(rack); // garante que associações como 'team' sejam atualizadas

        if (rack.getId() == null) {
            logger.warn("Rack not persisted: ID not generated");
            return null;
        }

        logger.infov("Rack created with id {0}", rack.getId());
        return RackMapper.toDTO(rack);
    }

    @Transactional
    public RackDTO updateRack(Long id, RackDTO rackDTO) {
        Rack existingRack = rackRepository.findById(id);

        if (existingRack == null) {
            logger.infov("Rack not found for update with id {0}", id);
            return null;
        }

        existingRack.setTeamId(rackDTO.getTeamId());
        existingRack.setSerialNumber(rackDTO.getSerialNumber());
        existingRack.setDefaultLocation(rackDTO.getDefaultLocation());
        existingRack.setStatus(rackDTO.getStatus());
        existingRack.setModifiedAt(LocalDateTime.now());

        em.flush(); // garante persistência no banco
        em.refresh(existingRack); // recarrega com associações corretas

        logger.infov("Rack updated with id {0}", id);
        return RackMapper.toDTO(existingRack);
    }
    @Transactional
    public RackDTO deleteRack(Long id) {
        Rack rack = rackRepository.findById(id);
        if (rack == null) {
            logger.infov("Rack not found for deletion with id {0}", id);
            return null;
        }
        rackRepository.delete(rack);
        logger.infov("Rack deleted with id {0}", id);
        return RackMapper.toDTO(rack);
    }

}
