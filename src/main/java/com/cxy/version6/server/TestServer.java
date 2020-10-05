package com.cxy.version6.server;
import com.cxy.version6.service.BlogService;
import com.cxy.version6.service.BlogServiceImpl;
import com.cxy.version6.service.UserService;
import com.cxy.version6.service.UserServiceImpl;

public class TestServer {
	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		BlogService blogService = new BlogServiceImpl();

		ServideProvider serviceProvider = new ServideProvider("127.0.0.1",8880);
		serviceProvider.provideService(userService);
		serviceProvider.provideService(blogService);

		RPCServer RPCServer1 = new NettyRPCServer(serviceProvider);
		RPCServer1.start(8880);
	}
}       
