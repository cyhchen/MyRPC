package com.cxy.version3.client;

import com.cxy.version3.common.Blog;
import com.cxy.version3.common.User;
import com.cxy.version3.service.BlogService;
import com.cxy.version3.service.UserService;

public class TestClient {
	public static void main(String[] args) {
		RPCClient nettyRPCClient = new NettyRPCClient("127.0.0.1", 8899);
		ClientProxy proxy = new ClientProxy(nettyRPCClient);
		UserService userService = proxy.getProxy(UserService.class);
		// 调用方法
		User userByUserId = userService.getUserByUserId(10);
		System.out.println("从服务端得到的user为：" + userByUserId);
//        BlogService blogService = proxy.getProxy(BlogService.class);
//		System.out.println("blogService is"+blogService);
//        Blog blogById = blogService.getBlogById(10000);
//        System.out.println("从服务端得到的blog为：" + blogById);
//		User user = User.builder().userName("张三").Id(100).sex(true).build();
//		Integer integer = userService.insertUserId(user);
//		System.out.println("向服务端插入数据：" + integer);
//
//		BlogService blogService = proxy.getProxy(BlogService.class);
//
//		Blog blogById = blogService.getBlogById(10000);
//		System.out.println("从服务端得到的blog为：" + blogById);
	
	}
}
