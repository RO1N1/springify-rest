package com.rohan.api.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rohan.api.constants.URI;
import com.rohan.api.entity.User;
import com.rohan.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value=URI.USERS)
@Api(tags="users")
public class UserController {

	private UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value="Finds All Users", notes="Returns a list of users in the app")
	@ApiResponses(value={
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	public List<User> findAll() {
		return service.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, value=URI.ID)
	@ApiOperation(value="Finds User by ID", notes="Returns a user by id if it exists in the app")
	@ApiResponses(value={
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=404, message="Not found"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	public User findOne(@PathVariable("id") String id) {
		return service.findOne(id);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value="Create User", notes="Creates a user in the app with unique email")
	@ApiResponses(value={
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=400, message="Bad Request"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	public User createUser(@RequestBody User user) {
		return service.createUser(user);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ApiOperation(value="Update User", notes="Updates an existing user")
	@ApiResponses(value={
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=404, message="Not found"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	public User updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	@ApiOperation(value="Delete User", notes="Deletes an existing user")
	@ApiResponses(value={
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=404, message="Not found"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	public void deleteUser(@RequestBody User user) {
		service.deleteUser(user);
	}
}
