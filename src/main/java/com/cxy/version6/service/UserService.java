package com.cxy.version6.service;

import com.cxy.version6.common.User;

public interface UserService {
	User getUserByUserId(Integer Id);
	Integer insertUserId(User user);
}
