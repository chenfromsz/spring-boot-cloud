package com.test.data.repositories;

import com.test.data.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends GraphRepository<User> {
    User findByName(@Param("name") String name);

    @Query("MATCH (u:User) WHERE u.name =~ ('(?i).*'+{name}+'.*') RETURN u")
    Collection<User> findByNameContaining(@Param("name") String name);

}


