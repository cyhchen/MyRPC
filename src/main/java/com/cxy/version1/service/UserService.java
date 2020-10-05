package com.cxy.version1.service;

import com.cxy.version1.common.User;

public interface UserService {
	User getUserByUserId(Integer Id);
	Integer insertUserId(User user);
}
