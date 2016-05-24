package com.test.web.controller;

import com.test.web.models.Role;
import com.test.web.service.RoleClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@RestController
@RefreshScope
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${cloud.sample.msg:World!}") String msg;

    @Autowired
    private Environment environment;

    @Autowired
    private RoleClient roleClient;
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/test")
    String test() {
        String configValue = environment.getProperty("spring.profiles.active", "undefined");
        logger.info("====configValue===="+configValue);
        return "Hello " + msg + "!";
    }

    @RequestMapping("/role")
    String role() {
        PagedResources<Role> roles = roleClient.findAll();
        List<Role> list = restTemplate.getForObject("http://data/roles", List.class);
        //roles.getContent()
        return "Role: ";
    }
}
