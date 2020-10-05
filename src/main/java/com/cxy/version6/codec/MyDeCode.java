package com.cxy.version6.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyDeCode extends ByteToMessageDecoder {
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception{
		short messageType = in.readShort();
		if(messageType != MessageType.REQUEST.getCode() && messageType != MessageType.RESPONSE.getCode()){
			System.out.println("can't support this type decode");
			return;
		}
		short serializerType  = in.readShort();
		Serializer serializer = Serializer.getSerializer(serializerType);
		int length = in.readInt();
		byte[] bytes = new byte[length];
		in.readBytes(bytes);
		
		Object deserializer = serializer.deserializer(bytes,messageType);
		list.add(deserializer);
	}
}
