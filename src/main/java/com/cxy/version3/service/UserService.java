package com.cxy.version3.service;

import com.cxy.version3.common.User;

public interface UserService {
	User getUserByUserId(Integer Id);
	Integer insertUserId(User user);
}
