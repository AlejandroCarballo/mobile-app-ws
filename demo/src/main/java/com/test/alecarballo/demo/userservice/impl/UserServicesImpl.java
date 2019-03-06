package com.test.alecarballo.demo.userservice.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.alecarballo.demo.controller.model.request.UserDetailsRequestModel;
import com.test.alecarballo.demo.controller.model.response.UserRest;
import com.test.alecarballo.demo.shared.Utils;
import com.test.alecarballo.demo.userservice.UserService;

@Service
public class UserServicesImpl implements UserService {

	Map<String, UserRest> users;

	Utils utils;

	public UserServicesImpl() {

	}

	@Autowired
	public UserServicesImpl(Utils utils) {
		this.utils = utils;

	}

	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {

		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());

		String userID = utils.generateUserId();
		returnValue.setUserId(userID);
		if (users == null)
			users = new HashMap<>();
		users.put(userID, returnValue);

		return returnValue;

	}

}
