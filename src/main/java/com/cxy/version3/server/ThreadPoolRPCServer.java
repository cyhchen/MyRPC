package com.cxy.version3.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRPCServer implements RPCServer {
	private ServideProvider serviceprovider;
	private ThreadPoolExecutor threadpool;
	
	public ThreadPoolRPCServer(ServideProvider serviceprovider){
		this.serviceprovider = serviceprovider;
		this.threadpool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),1000,60,
			TimeUnit.SECONDS,new ArrayBlockingQueue<>(100));
	}
	
	public ThreadPoolRPCServer(ServideProvider serviceprovider, int corePoolSize, int maximumPoolSize, int keepAliveTime, TimeUnit unit, BlockingDeque<Runnable> workQueue){
		this.serviceprovider = serviceprovider;
		this.threadpool = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue);
	}
	
	@Override
	public void start(int port){
		System.out.println("服务器启动");
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			while (true) {
				Socket socket = serverSocket.accept();
				threadpool.execute(new WorkThread(socket, serviceprovider));
			}
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("服务器启动失败");
		}
	}
	
	@Override
	public void end(){
		
	}
}
