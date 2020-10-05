package com.cxy.version0.server;

import com.cxy.version0.common.User;
import com.cxy.version0.service.UserService;
import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
	public User getUserByUserId(Integer Id){
		System.out.println("查询Id："+Id+"用户");
		Random random = new Random();
		User user = User.builder().userName(UUID.randomUUID().toString()).sex(random.nextBoolean()).Id(Id).build();
		return user;
	}
}
