package com.cxy.version2.client;

import com.cxy.version2.common.RPCRequest;
import com.cxy.version2.common.RPCResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOClient {
	public static RPCResponse sendRequest(String host, int port, RPCRequest request){
		try{
			Socket socket = new Socket(host,port);
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(request);
			outputStream.flush();
			RPCResponse response = (RPCResponse) inputStream.readObject();
			return response;
		}catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("客户端IO异常");
			return null;
		}
	}
}
