package com.cxy.version6.codec;

public interface Serializer {
	byte[] serializer(Object obj);
	Object deserializer(byte[] bytes, int messageType);
	int getType();
	static Serializer getSerializer(int code){
		switch (code){
			case 0: 
				return new ObjectSerializer();
			case 1:
				return new JsonSerializer();
			default:
				return null;
		}
	}
}
