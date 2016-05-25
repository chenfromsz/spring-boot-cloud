package com.test.data.services;

import com.test.data.domain.User;
import com.test.data.model.UserQo;
import com.test.data.repositories.UserRepository;
import org.neo4j.ogm.cypher.BooleanOperator;
import org.neo4j.ogm.cypher.ComparisonOperator;
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
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PagesService<User> userPagesService;

    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        User user = userRepository.findOne(id);
        userRepository.delete(user);
    }

    public User findByName(String username){
        return userRepository.findByName(username);
    }

    public Page<User> findPage(UserQo userQo){
        Pageable pageable = new PageRequest(userQo.getPage(), userQo.getSize(), new Sort(Sort.Direction.ASC, "id"));

        Filters filters = new Filters();
        if (!StringUtils.isEmpty(userQo.getName())) {
            Filter filter = new Filter("name", userQo.getName());
            filters.add(filter);
        }
        if (!StringUtils.isEmpty(userQo.getCreate())) {
            Filter filter = new Filter("create", userQo.getCreate());
            filter.setComparisonOperator(ComparisonOperator.GREATER_THAN);
            filter.setBooleanOperator(BooleanOperator.AND);
            filters.add(filter);
        }

        return userPagesService.findAll(User.class, pageable, filters);
    }
}
