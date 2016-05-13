package com.test.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.test.web.clients.UserClient;
import com.test.web.models.User;
import com.test.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserClient userClient;
	
	@RequestMapping(value="/users")
	public ResponseEntity<Collection<User>> getUserList(){
//		List<User> users = userService.searchAll();
		Collection<User> users = userClient.findAll().getContent(); //new ArrayList<>();
//		User user = userClient.findByName("user");
//		users.add(user);
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}
}
