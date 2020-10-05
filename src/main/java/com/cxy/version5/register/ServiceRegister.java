package com.cxy.version5.register;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public interface ServiceRegister {
	void register(String serviceName, InetSocketAddress address);
	InetSocketAddress serviceDiscovery(String serviceName);

}
