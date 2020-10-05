package com.cxy.version4.service;

import com.cxy.version4.common.User;
import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
	
	@Override
	public User getUserByUserId(Integer Id){
		System.out.println("查询Id："+Id+"用户");
		Random random = new Random();
		User user = User.builder().userName(UUID.randomUUID().toString()).sex(random.nextBoolean()).Id(Id).build();
		return user;
	}
	
	@Override
	public Integer insertUserId(User user){
		System.out.println("User插入完毕" + user);
		return user.getId();
	}
	
}
