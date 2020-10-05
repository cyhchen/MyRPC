package com.cxy.version4.server;

import java.util.HashMap;
import java.util.Map;

public class ServideProvider {
	private Map<String, Object> map;
	public ServideProvider(){
		this.map = new HashMap<>();
	}
	
	public void provideService(Object service){
		String serviceName = service.getClass().getName();
		Class<?>[] interfaces = service.getClass().getInterfaces();
		for(Class clazz:interfaces){
			map.put(clazz.getName(),service);
		}
	}
	
	public Object getService(String interfaceName){
		return map.get(interfaceName);
	}
}
