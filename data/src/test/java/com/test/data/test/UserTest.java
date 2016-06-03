package com.test.data.test;

import com.test.data.domain.Role;
import com.test.data.domain.Unit;
import com.test.data.domain.User;
import com.test.data.model.UserQo;
import com.test.data.repositories.RoleRepository;
import com.test.data.repositories.UnitRepository;
import com.test.data.repositories.UserRepository;
import com.test.data.services.PagesService;
import com.test.data.services.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alan on 2016/3/28.
 */
@ContextConfiguration(classes = {Neo4jConfig.class, PagesService.class, UserService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {
    private static Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Autowired
    UnitRepository unitRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    //@Test
    public void initData() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        unitRepository.deleteAll();

        Unit unit = new Unit();
        unit.setName("开发部");
        unit.setCreate(new Date());

        unitRepository.save(unit);
        Assert.notNull(unit.getId());

        Role role = new Role();
        role.setName("admin");
        role.setCreate(new Date());

        roleRepository.save(role);
        Assert.notNull(role.getId());

        Role role1 = new Role();
        role1.setName("manage");
        role1.setCreate(new Date());

        roleRepository.save(role1);
        Assert.notNull(role1.getId());

        User user = new User();
        user.setName("user");
        user.setSex(1);
        user.setEmail("user@email.com");
        user.setCreate(new Date());

        user.beBelong(unit,"安排");

        user.addOwner(role, "分配");
        user.addOwner(role1, "分配");

        userRepository.save(user);
        Assert.notNull(user.getId());
    }

    @Test
    public void getPage() throws Exception{
        Pageable pageable = new PageRequest( 0, 10, new Sort(Sort.Direction.ASC, "id"));

        UserQo userQo = new UserQo();
        userQo.setName("user");
        userQo.setCreate(new SimpleDateFormat("yyyy-MM-dd").parse("2016-04-29"));

        Page<User> users = userService.findPage(userQo);
        Assert.notNull(users);
        for(User user : users.getContent()){
            logger.info("==== user ==== name:{}, unot: {}, role: {}",
                    user.getName(), user.getBelong().getUnit().getName(), user.getOwners().get(0).getRole().getName()
            );
        }
    }
}
