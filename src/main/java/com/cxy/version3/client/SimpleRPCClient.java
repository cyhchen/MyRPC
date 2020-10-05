package com.cxy.version3.client;

import com.cxy.version3.common.RPCResponse;
import com.cxy.version3.common.RPCRequest;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimpleRPCClient implements RPCClient{
	private String host;
	private int port;
	
	public RPCResponse sendRequest(RPCRequest request){
		try{
			Socket socket = new Socket(host, port);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			
			System.out.println(request);
			oos.writeObject(request);
			oos.flush();
			
			RPCResponse response = (RPCResponse) ois.readObject();
			return response;
		} catch (IOException | ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}
	}
}
