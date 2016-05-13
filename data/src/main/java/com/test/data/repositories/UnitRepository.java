package com.test.data.repositories;

import com.test.data.domain.Unit;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "units", path = "units")
public interface UnitRepository extends GraphRepository<Unit> {
}


