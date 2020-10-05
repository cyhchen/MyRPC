package com.cxy.version4.codec;


import com.cxy.version4.common.RPCRequest;
import com.cxy.version4.common.RPCResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import com.cxy.version4.codec.MessageType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyEncode extends MessageToByteEncoder {
	private Serializer serializer;
	
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out){
		System.out.println(msg.getClass().getName());
		if(msg.getClass() == RPCRequest.class){
			out.writeShort(MessageType.REQUEST.getCode());
		}else if(msg.getClass() == RPCResponse.class){
			out.writeShort(MessageType.RESPONSE.getCode());
		}else{
			System.out.println("can't support this type encode");
			return;
		}
		out.writeShort(serializer.getType());
		byte[] bytes = serializer.serializer(msg);
		out.writeInt(bytes.length);
		out.writeBytes(bytes);
	}
}
