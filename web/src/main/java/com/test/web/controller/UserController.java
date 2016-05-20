package com.test.web.controller;

import com.test.web.models.User;
import com.test.web.service.UserClient;
import com.test.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    UserClient userClient;

    @RequestMapping("/index")
    public String index() throws Exception{
        return "user/index";
    }

    @RequestMapping(value="/{name}")
    public String show(ModelMap model,@PathVariable String name) {
        User user = userService.getUserByName(name);
        model.addAttribute("user",user);
        return "user/show";
    }

}
