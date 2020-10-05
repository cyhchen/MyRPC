package com.cxy.version4.service;

import com.cxy.version4.common.User;

public interface UserService {
	User getUserByUserId(Integer Id);
	Integer insertUserId(User user);
}
