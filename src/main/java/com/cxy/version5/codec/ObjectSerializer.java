package com.cxy.version5.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSerializer implements Serializer {
	 
	@Override
	public byte[] serializer(Object obj){
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try{
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		}catch (IOException e){
			e.printStackTrace();
		}
		return bytes;
	}
	
	@Override
	public Object deserializer(byte[] bytes, int messageType){
		Object obj = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		try{
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		}catch (IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return obj;
	}
	
	@Override
	public int getType(){
		return 0;
	}
}
