package com.cxy.version5.register;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class ZkServiceRegister implements ServiceRegister {
	private CuratorFramework client;
	private static final String ROOT_PATH = "MyRPC";
	
	public ZkServiceRegister(){
		RetryPolicy policy = new ExponentialBackoffRetry(1000,3);
		this.client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").sessionTimeoutMs(40000).retryPolicy(policy).namespace(ROOT_PATH).build();
		this.client.start();
		System.out.println("Zookeeper连接成功");
	}
	
	@Override
	public void register(String serviceName, InetSocketAddress address){
		try{
			if(client.checkExists().forPath("/"+serviceName) == null){
				client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/"+serviceName);
			}
			String path = "/" + serviceName + "/" + getAddress(address);
			client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public InetSocketAddress serviceDiscovery(String address){
		try {
			List<String> strs = client.getChildren().forPath("/" + address);
			String str0 = strs.get(0);
			return parseAddress(str0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	private String getAddress(InetSocketAddress address){
		return address.getHostName() + ":" + address.getPort();
	}
	
	private InetSocketAddress parseAddress(String address){
		String[] str = address.split(":");
		return new InetSocketAddress(str[0],Integer.parseInt(str[1]));
	}
}
