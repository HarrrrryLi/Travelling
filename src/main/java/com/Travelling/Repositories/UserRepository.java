package com.Travelling.Repositories;

import com.Travelling.Repositories.Entities.User;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT CASE  WHEN (count(uid)> 0) THEN true ELSE false END FROM users WHERE email = :email", nativeQuery = true)
    int existsByEmail(@Param("email") String email);

    @Query(value = "SELECT CASE  WHEN (count(uid)> 0) THEN true ELSE false END FROM users WHERE username = :username", nativeQuery = true)
    int existsByUsername(@Param("username") String username);


}
