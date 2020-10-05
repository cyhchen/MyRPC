package com.cxy.version6.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleRPCServer implements RPCServer {
	private ServideProvider map;
	
	public SimpleRPCServer(ServideProvider map){
		this.map = map;
	}
	
	@Override
	public void start(int port){
		try{
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("服务端启动");
			while(true){
				Socket socket = serverSocket.accept();
				new Thread(new WorkThread(socket,map)).start();
			}
		}catch (IOException e){
			e.printStackTrace();
			System.out.println("服务器启动失败");
		}
	}
	@Override
	public void end(){
			
	}

}
