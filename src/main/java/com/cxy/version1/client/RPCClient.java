package com.cxy.version1.client;

import com.cxy.version1.service.UserService;
import com.cxy.version1.common.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class RPCClient {
	public static void main(String[] args){
		ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
		UserService proxy = clientProxy.getProxy(UserService.class);
		User user = proxy.getUserByUserId(10);
		System.out.println("服务器端返回User:" + user);
		Integer res = proxy.insertUserId(User.builder().userName("alice").Id(11110).sex(true).build());
		System.out.println("服务器插入User"+res);
	}
}
