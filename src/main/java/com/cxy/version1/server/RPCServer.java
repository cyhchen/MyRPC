package com.cxy.version1.server;

import com.cxy.version1.common.RPCRequest;
import com.cxy.version1.common.RPCResponse;
import com.cxy.version1.common.User;
import com.cxy.version1.service.UserService;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {
	public static void main(String[] args) {
		UserService service = new UserServiceImpl();
		try {
			ServerSocket serverSocket = new ServerSocket(8899);
			System.out.println("服务端启动");
			while (true) {
				Socket socket = serverSocket.accept();
				new Thread(() -> {
					try {
						ObjectOutputStream outputStream = new ObjectOutputStream(
							socket.getOutputStream());
						ObjectInputStream inputStream = new ObjectInputStream(
							socket.getInputStream());
						RPCRequest request = (RPCRequest) inputStream.readObject();
						Method method = service.getClass().getMethod(request.getMethodName(),request.getParamsTypes());
						Object invokeres = method.invoke(service,request.getParams());
						outputStream.writeObject(RPCResponse.success(invokeres));
						outputStream.flush();
					} catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
						System.out.println("IO 异常!");
					}
				}).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("服务器启动失败！");
		}
	}
}
