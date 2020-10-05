package com.cxy.version2.client;

import com.cxy.version2.common.Blog;
import com.cxy.version2.common.User;
import com.cxy.version2.server.UserServiceImpl;
import com.cxy.version2.service.BlogService;
import com.cxy.version2.service.UserService;

public class RPCClient {
	public static void main(String[] args){
		
		ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
		
		UserService proxy = clientProxy.getProxy(UserService.class);
		User user = proxy.getUserByUserId(10);
		System.out.println("服务器端返回User:" + user);
		Integer res = proxy.insertUserId(User.builder().userName("alice").Id(11110).sex(true).build());
		System.out.println("服务器插入User"+res);
		
		BlogService blogService = clientProxy.getProxy(BlogService.class);
		Blog blog = blogService.getBlogById(10000);
		System.out.println("Blog:"+blog);
		
		
	}
}
