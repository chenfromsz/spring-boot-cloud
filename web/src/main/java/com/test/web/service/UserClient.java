package com.test.web.service;


import com.test.web.models.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("data")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    PagedResources<User> findAll();

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    User findById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.POST, value = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void createUser(@RequestBody User user);

}
