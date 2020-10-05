package com.cxy.version6.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cxy.version6.common.RPCRequest;
import com.cxy.version6.common.RPCResponse;

public class JsonSerializer implements Serializer {
	
	@Override
	public byte[] serializer(Object obj){
		byte[] bytes = JSONObject.toJSONBytes(obj);
		return bytes;
	}
	@Override
	public Object deserializer(byte[] bytes, int messageType){
		Object obj = null;
		switch (messageType){
			case 0:
				RPCRequest request = JSON.parseObject(bytes, RPCRequest.class);
				System.out.println("json_request is:"+request);
				Object[] objects = new Object[request.getParams().length];
				for(int i = 0;i < objects.length;i++){
					Class<?> parasType = request.getParamsTypes()[i];
					if(!parasType.isAssignableFrom(request.getParams()[i].getClass())){
						System.out.println("parastype is:"+parasType);
						System.out.println("request.getParams()[i].getClass() is:"+request.getParams()[i].getClass());
						objects[i] = JSONObject.toJavaObject((JSONObject)request.getParams()[i],request.getParamsTypes()[i]);
					}
					else{
						System.out.println("parastype is:"+parasType);
						System.out.println("request.getParams()[i].getClass() is:"+request.getParams()[i].getClass());
						objects[i] = request.getParams()[i];
					}
					
				}
				request.setParams(objects);
				obj = request;
				break;
			case 1:
				RPCResponse response = JSON.parseObject(bytes, RPCResponse.class);
				Class<?> dataType = response.getDataType();
				if(!dataType.isAssignableFrom(response.getData().getClass())){
					response.setData(JSON.toJavaObject((JSONObject) response.getData(), dataType));
				}
				obj = response;
				break;
			default:
				System.out.println("can't support this kind of message");
				throw new RuntimeException();
		}
		return obj;
	}

	@Override
	public int getType() {
		return 1;
	}
}
