package com.cxy.version1.client;

import com.cxy.version1.common.RPCRequest;
import com.cxy.version1.common.RPCResponse;
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
		RPCResponse response = IOClient.sendRequest(host, port, request);
		return response.getData();
	}
	
	<T> T getProxy(Class<T> clazz){
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},this);
	}
}
