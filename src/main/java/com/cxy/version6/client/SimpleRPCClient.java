package com.cxy.version6.client;

import com.cxy.version6.common.RPCRequest;
import com.cxy.version6.common.RPCResponse;
import com.cxy.version6.register.ZkServiceRegister;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimpleRPCClient implements RPCClient {
	private ZkServiceRegister zkServiceRegister;
	
	public RPCResponse sendRequest(RPCRequest request){
		try{
			InetSocketAddress address = zkServiceRegister.serviceDiscovery(request.getInterfaceName());
			String host = address.getHostName();
			int port = address.getPort();
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
