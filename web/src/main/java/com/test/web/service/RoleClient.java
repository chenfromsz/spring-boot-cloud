package com.test.web.service;


import com.test.web.models.Role;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("data")
public interface RoleClient {

    @RequestMapping(method = RequestMethod.GET, value = "/roles")
    PagedResources<Role> findAll();

    @RequestMapping(method = RequestMethod.GET, value = "/roles/{id}")
    Role findById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.POST, value = "/roles",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void createRole(@RequestBody Role role);

}
