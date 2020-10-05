package com.cxy.version6.server;

import com.cxy.version6.common.RPCRequest;
import com.cxy.version6.common.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NettyRPCServerHandler extends SimpleChannelInboundHandler<RPCRequest> {
	private ServideProvider servideProvider;
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RPCRequest msg) throws Exception{
		RPCResponse response = getResponse(msg);
		ctx.writeAndFlush(response);
		ctx.close();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception{
		cause.printStackTrace();;
		ctx.close();
	}
	
	RPCResponse getResponse(RPCRequest request){
		String interfaceName = request.getInterfaceName();
		Object service = servideProvider.getService(interfaceName);
		Method method = null;
		try {
			System.out.println("request is: "+request);
			method = service.getClass().getMethod(request.getMethodName(),request.getParamsTypes());
			Object invoke = method.invoke(service, request.getParams());
			return RPCResponse.success(invoke);
		}catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
			System.out.println("方法执行错误");
			return RPCResponse.failed();
		}
	}
}
