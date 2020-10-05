package com.cxy.version5.service;

import com.cxy.version5.common.User;

public interface UserService {
	User getUserByUserId(Integer Id);
	Integer insertUserId(User user);
}
