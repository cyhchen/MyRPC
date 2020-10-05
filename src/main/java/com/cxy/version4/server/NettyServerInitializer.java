package com.cxy.version4.server;

import com.cxy.version4.codec.JsonSerializer;
import com.cxy.version4.codec.MyDeCode;
import com.cxy.version4.codec.MyEncode;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
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
