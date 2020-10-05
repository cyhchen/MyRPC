package com.cxy.version3.server;
import com.cxy.version3.service.BlogService;
import com.cxy.version3.service.BlogServiceImpl;
import com.cxy.version3.service.UserService;
import com.cxy.version3.service.UserServiceImpl;

public class TestServer {
	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		BlogService blogService = new BlogServiceImpl();

		ServideProvider serviceProvider = new ServideProvider();
		serviceProvider.provideService(userService);
		serviceProvider.provideService(blogService);

		RPCServer RPCServer = new NettyRPCServer(serviceProvider);
		RPCServer.start(8899);
	}
}       
