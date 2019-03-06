package com.test.alecarballo.demo.userservice;

import com.test.alecarballo.demo.controller.model.request.UserDetailsRequestModel;
import com.test.alecarballo.demo.controller.model.response.UserRest;

public interface UserService {
	
	 UserRest createUser (UserDetailsRequestModel model);

}
