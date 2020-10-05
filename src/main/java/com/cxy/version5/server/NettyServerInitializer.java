package com.cxy.version5.server;

import com.cxy.version5.codec.JsonSerializer;
import com.cxy.version5.codec.MyDeCode;
import com.cxy.version5.codec.MyEncode;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
	private ServideProvider servideProvider;
	@Override
	protected void initChannel(SocketChannel ch) throws Exception{
		ChannelPipeline pipeline = ch.pipeline();
		
		pipeline.addLast(new MyDeCode());
		pipeline.addLast(new MyEncode(new JsonSerializer()));
		
		pipeline.addLast(new NettyRPCServerHandler(servideProvider));
	}
}
