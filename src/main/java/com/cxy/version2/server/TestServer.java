package com.cxy.version2.server;
import com.cxy.version2.service.BlogService;
import com.cxy.version2.service.RPCServer;
import com.cxy.version2.service.UserService;

public class TestServer {
	public static void main(String[] args){
		UserService userService = new UserServiceImpl();
		BlogService blogService = new BlogServiceImpl();

		ServideProvider servideProvider = new ServideProvider();
		servideProvider.provideService(userService);
		servideProvider.provideService(blogService);
		//RPCServer server = new SimpleRPCServer(servideProvider);
		//server.start(8899);
		RPCServer server = new ThreadPoolRPCServer(servideProvider);
		server.start(8890);
	}
}       
