package com.test.data.services;

import com.test.data.domain.Unit;
import com.test.data.model.UnitQo;
import com.test.data.repositories.UnitRepository;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private PagesService<Unit> unitPagesService;

    public Unit findById(Long id) {
        return unitRepository.findOne(id);
    }

    public Unit create(Unit unit) {
        return unitRepository.save(unit);
    }

    public Unit update(Unit unit) {
        return unitRepository.save(unit);
    }

    public void delete(Long id) {
        Unit unit = unitRepository.findOne(id);
        unitRepository.delete(unit);
    }

    public Iterable<Unit> findAll(){
        return unitRepository.findAll();
    }

    public Page<Unit> findPage(UnitQo unitQo){
        Pageable pageable = new PageRequest(unitQo.getPage(), unitQo.getSize(), new Sort(Sort.Direction.ASC, "id"));

        Filters filters = new Filters();
        if (!StringUtils.isEmpty(unitQo.getName())) {
            Filter filter = new Filter("name", unitQo.getName());
            filters.add(filter);
        }

        return unitPagesService.findAll(Unit.class, pageable, filters);
    }
}
