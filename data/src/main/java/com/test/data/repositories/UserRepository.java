package com.test.data.repositories;

import com.test.data.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends GraphRepository<User> {
    User findByName(@Param("name") String name);

    @Query("MATCH (u:User) WHERE u.name =~ ('(?i).*'+{name}+'.*') RETURN u")
    Collection<User> findByNameContaining(@Param("name") String name);

    @Query("MATCH (u:User)<-[:拥有]-(o:Owner) RETURN u.name AS user, collect(o.name) AS cast LIMIT {limit}")
    List<Map<String,Object>> graph(@Param("limit") int limit);

}


