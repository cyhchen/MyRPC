package com.cxy.version0.server;

import com.cxy.version0.common.User;
import com.cxy.version0.service.UserService;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
						Integer Id = inputStream.readInt();
						User user = service.getUserByUserId(Id);
						outputStream.writeObject(user);
						outputStream.flush();
					} catch (IOException e) {
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
