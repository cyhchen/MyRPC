package com.cxy.version5.server;
import com.cxy.version5.service.BlogService;
import com.cxy.version5.service.BlogServiceImpl;
import com.cxy.version5.service.UserService;
import com.cxy.version5.service.UserServiceImpl;

public class TestServer {
	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		BlogService blogService = new BlogServiceImpl();

		ServideProvider serviceProvider = new ServideProvider("127.0.0.1",8899);
		serviceProvider.provideService(userService);
		serviceProvider.provideService(blogService);

		RPCServer RPCServer = new NettyRPCServer(serviceProvider);
		RPCServer.start(8899);
	}
}       
