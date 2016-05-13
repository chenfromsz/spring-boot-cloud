package com.test.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserService {
	 @Autowired	 
	 RestTemplate restTemplate;
	 
	 //@HystrixCommand(fallbackMethod = "fallbackSearchAll")
	 public List<User> searchAll() {
//		 List<User> users = restTemplate.getForObject("http://data/users", List.class);
		 Map<String, Object> params = new HashMap<>();
		 params.put("name", "user");
		 User user = restTemplate.getForObject("http://data/users/search/findByName?name={name}", User.class, params);
		 List<User> users = new ArrayList<>();
		 users.add(user);
		 return users;
	 }	 
	 private List<User> fallbackSearchAll() {
		 System.out.println("HystrixCommand fallbackMethod handle!");
		 List<User> ls = new ArrayList<User>();
		 User user = new User();
		 user.setName("one name");
		 ls.add(user);
		 return ls;
	 }
}
