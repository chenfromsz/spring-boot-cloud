package com.test.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableHystrix
@RestController
@ComponentScan
@EnableFeignClients
public class WebApplication {
    @Value("${cloud.sample.msg:World!}")
    String bar;

    private static Logger logger = LoggerFactory.getLogger(WebApplication.class);

    @Autowired
    private Environment env;

    @RequestMapping("/hello")
    String hello() {
        String configValue = env.getProperty("eureka.client.serviceUrl.defaultZone", "undefined");
        logger.info("====configValue===="+configValue);
        return "Hello " + bar + "!";
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
