package com.cxy.version0.client;

import com.cxy.version0.common.User;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class RPCClient {
	public static void main(String[] args){
		try{
			Socket socket = new Socket("127.0.0.1",8899);
			ObjectOutputStream outputstream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			outputstream.writeInt(new Random().nextInt());
			outputstream.flush();
			User user = (User) inputStream.readObject();
			System.out.println("服务器端返回User:"+user);
		}catch (IOException | ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("client failed!");
		}
	}
}
