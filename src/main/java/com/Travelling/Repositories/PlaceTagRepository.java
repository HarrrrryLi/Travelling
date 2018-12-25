package com.Travelling.Repositories;

import com.Travelling.Repositories.Entities.PlaceTag;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PlaceTagRepository extends SimpleJpaRepository<PlaceTag, Integer> {
    private EntityManager em;
    public PlaceTagRepository(EntityManager em){
        super(PlaceTag.class,em);
        this.em = em;
    }

}
