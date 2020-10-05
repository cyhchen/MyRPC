package com.cxy.version2.service;

import com.cxy.version2.common.User;

public interface UserService {
	User getUserByUserId(Integer Id);
	Integer insertUserId(User user);
}
