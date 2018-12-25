package com.Travelling.Repositories;

import com.Travelling.Repositories.Entities.Place;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PlaceRepository extends SimpleJpaRepository<Place, Integer> {
    private EntityManager em;
    public PlaceRepository(EntityManager em){
        super(Place.class,em);
        this.em = em;
    }

}
