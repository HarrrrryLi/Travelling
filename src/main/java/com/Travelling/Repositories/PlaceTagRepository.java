package com.Travelling.Repositories;

import com.Travelling.Repositories.Entities.CompositeIds.PlaceTagId;
import com.Travelling.Repositories.Entities.Place;
import com.Travelling.Repositories.Entities.PlaceTag;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PlaceTagRepository extends SimpleJpaRepository<PlaceTag, PlaceTagId> {
    private EntityManager em;
    public PlaceTagRepository(EntityManager em){
        super(PlaceTag.class,em);
        this.em = em;
    }

    @Transactional
    @Override
    public <S extends PlaceTag> S save(S entity) {

        if (!this.existsById(new PlaceTagId(entity.getPid(),entity.getTid()))) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

}
