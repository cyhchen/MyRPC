package com.cxy.version6.register;

import java.net.InetSocketAddress;

public interface ServiceRegister {
	void register(String serviceName, InetSocketAddress address);
	InetSocketAddress serviceDiscovery(String serviceName);

}
