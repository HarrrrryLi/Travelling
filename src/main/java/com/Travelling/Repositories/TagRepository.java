package com.Travelling.Repositories;

import com.Travelling.Repositories.Entities.Tag;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class TagRepository extends SimpleJpaRepository<Tag, Integer> {
    private EntityManager em;
    public TagRepository(EntityManager em){
        super(Tag.class,em);
        this.em = em;
    }

}
