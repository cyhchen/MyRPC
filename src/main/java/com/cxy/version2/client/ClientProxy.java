package com.cxy.version2.client;

import com.cxy.version2.common.RPCRequest;
import com.cxy.version2.common.RPCResponse;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClientProxy implements InvocationHandler {
	private String host;
	private int port;
	@Override
	public Object invoke(Object proxy, Method method, Object[] args){
		RPCRequest request = RPCRequest.builder().interfaceName(method.getDeclaringClass().getName()).methodName(method.getName()).params(args).paramsTypes(method.getParameterTypes()).build();
		System.out.println("interfaceName: s"+method.getDeclaringClass().getName());
		RPCResponse response = IOClient.sendRequest(host, port, request);
		return response.getData();
	}
	
	<T> T getProxy(Class<T> clazz){
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},this);
	}
}
