package com.ctw.workstation.service;

import com.ctw.workstation.dto.RackAssetDTO;
import com.ctw.workstation.entity.Rack;
import com.ctw.workstation.entity.RackAsset;
import com.ctw.workstation.mappers.RackAssetMapper;
import com.ctw.workstation.repository.RackAssetRepository;
import com.ctw.workstation.repository.RackRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class RackAssetService {

    @Inject
    RackAssetRepository rackAssetRepository;

    @Inject
    RackRepository rackRepository;

    @Inject
    Logger logger;

    @PersistenceContext
    EntityManager em;

    public List<RackAssetDTO> getAllRackAssets() {
        List<RackAsset> entities = rackAssetRepository.listAll();
        logger.infov("Returned {0} rack assets", entities.size());
        return entities.stream().map(RackAssetMapper::toDTO).collect(Collectors.toList());
    }

    public RackAssetDTO getRackAssetById(Long id) {
        RackAsset rackAsset = rackAssetRepository.findById(id);
        if (rackAsset == null) {
            logger.infov("Rack asset not found with id {0}", id);
            return null;
        }
        logger.infov("Returned rack asset with id {0}", id);
        return RackAssetMapper.toDTO(rackAsset);
    }

    @Transactional
    public RackAssetDTO createRackAsset(RackAssetDTO dto) {
        RackAsset entity = RackAssetMapper.toEntity(dto);

        if (dto.getRackId() != null) {
            Rack rack = rackRepository.findById(dto.getRackId());
            entity.setRack(rack);
        }

        rackAssetRepository.persist(entity);
        em.flush();
        em.refresh(entity);

        if (entity.getId() == null) {
            logger.warn("Rack asset not persisted: ID not generated");
            return null;
        }

        logger.infov("Rack asset created with id {0}", entity.getId());
        return RackAssetMapper.toDTO(entity);
    }

    @Transactional
    public RackAssetDTO updateRackAsset(Long id, RackAssetDTO dto) {
        RackAsset existing = rackAssetRepository.findById(id);

        if (existing == null) {
            logger.infov("Rack asset not found for update with id {0}", id);
            return null;
        }

        existing.setAssetTag(dto.getAssetTag());
        existing.setRackId(dto.getRackId());

        if (dto.getRackId() != null) {
            Rack rack = rackRepository.findById(dto.getRackId());
            existing.setRack(rack);
        }

        em.flush();
        em.refresh(existing);

        logger.infov("Rack asset updated with id {0}", id);
        return RackAssetMapper.toDTO(existing);
    }
}
