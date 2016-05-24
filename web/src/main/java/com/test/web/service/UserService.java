package com.test.web.service;

import java.util.*;

import com.test.web.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserService {
	 @Autowired @LoadBalanced
	 RestTemplate restTemplate;
	 
	 @HystrixCommand(fallbackMethod = "getUserFallback")
	 public User getUserByName(String name) {
		 Map<String, Object> params = new HashMap<>();
		 params.put("name", name);
		 User user = restTemplate.getForObject("http://data/users/search/findByName?name={name}", User.class, params);

		 return user;
	 }

	 private User getUserFallback(String name) {
		 User user = new User();
		 user.setName(name + " not find");
		 user.setEmail("user email");

		 Belong belong = new Belong();
		 Unit unit = new Unit();
		 unit.setName("unit name");
		 belong.setUnit(unit);
		 user.setBelong(belong);

		 Owner owner = new Owner();
		 Role role = new Role();
		 role.setName("role name");
		 owner.setRole(role);

		 List<Owner> owners = new ArrayList<>();
		 owners.add(owner);
		 user.setOwners(owners);

		 return user;
	 }
}
