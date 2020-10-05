package com.cxy.version5.server;

import com.cxy.version5.register.ZkServiceRegister;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ServideProvider {
	private Map<String, Object> map;
	private ZkServiceRegister zkServiceRegister;
	private String host;
	private int port;
	
	public ServideProvider(String host, int port){
		this.host = host;
		this.port = port;
		this.map = new HashMap<>();
		this.zkServiceRegister = new ZkServiceRegister();
	}
	
	public void provideService(Object service){
		String serviceName = service.getClass().getName();
		Class<?>[] interfaces = service.getClass().getInterfaces();
		for(Class clazz:interfaces){
			map.put(clazz.getName(),service);
			zkServiceRegister.register(clazz.getName(),new InetSocketAddress(host,port));
		}
	}
	
	public Object getService(String interfaceName){
		return map.get(interfaceName);
	}
}
