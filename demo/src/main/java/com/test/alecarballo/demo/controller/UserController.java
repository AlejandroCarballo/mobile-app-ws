package com.test.alecarballo.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.alecarballo.demo.controller.model.request.UpdateUserDetailsRequestModel;
import com.test.alecarballo.demo.controller.model.request.UserDetailsRequestModel;
import com.test.alecarballo.demo.controller.model.response.UserRest;

@RestController
@RequestMapping("/users")
public class UserController {

	Map<String, UserRest> users;

	@GetMapping()
	public String getUser(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "sort", required = false) String sort) {
		return "get user was called with page =" + page + "and limit =" + limit + "and sort is =" + sort;
	}

	@GetMapping(path = "/{userID}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userID) {

		if (users.containsKey(userID)) {
			return new ResponseEntity<UserRest>(users.get(userID), HttpStatus.OK);
		} else
			return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);

	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {

		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());

		String userID = UUID.randomUUID().toString();
		returnValue.setUserId(userID);
		if (users == null) {
			users = new HashMap<>();
			users.put(userID, returnValue);
		}

		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}

	@PutMapping(path = "/{userID}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String userId,
			@Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {

		UserRest storedUserDetails = users.get(userId);
		storedUserDetails.setName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());

		users.put(userId, storedUserDetails);

		return storedUserDetails;

	}

	/*
	 * @DeleteMapping(path="{/id}") public
	 * ResponseEntity<void>deleteUser(@PathVariable String id) { users.remove(id);
	 * 
	 * return ResponseEntity.noContent().build(); }
	 */

}
