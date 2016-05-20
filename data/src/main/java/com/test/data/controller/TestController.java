package com.test.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${cloud.config.test:World!}") String msg;

    @Autowired
    private Environment environment;

    @RequestMapping("/test")
    String test() {
        String configValue = environment.getProperty("spring.profiles.active", "undefined");
        logger.info("====configValue===="+configValue);
        return "Hello " + msg + "!";
    }
}
