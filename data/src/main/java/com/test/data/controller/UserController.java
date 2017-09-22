package com.test.data.controller;

import com.test.data.domain.User;
import com.test.data.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/findByNameContaining")
    Collection<User> findByNameContaining(HttpServletRequest request) {
        String name = request.getParameter("name");
        return userService.findByNameContaining(name);
    }

    @RequestMapping("/findByName")
    User findByName(HttpServletRequest request) {
        String name = request.getParameter("name");
        return userService.findByName(name);
    }
}
