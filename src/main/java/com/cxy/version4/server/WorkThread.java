package com.cxy.version4.server;

import com.cxy.version4.common.RPCRequest;
import com.cxy.version4.common.RPCResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WorkThread implements Runnable{
	private Socket socket;
	private ServideProvider map;
	@Override
	public void run(){
		try{
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			RPCRequest request = (RPCRequest) ois.readObject(); 
			RPCResponse response = getResponse(request);
			oos.writeObject(response);
			oos.flush();
		}catch (IOException | ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("从IO中读取错误");
		}
	}
	
	private RPCResponse getResponse(RPCRequest request){
		String interfaceName = request.getInterfaceName();
		Object service = map.getService(interfaceName);
		Method method = null;
		try{
			method = service.getClass().getMethod(request.getMethodName(),request.getParamsTypes());
			Object invoke = method.invoke(service,request.getParams());
			return RPCResponse.success(invoke);
		}catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
			System.out.println("方法执行错误");
			return RPCResponse.failed();
		}
	}
}
