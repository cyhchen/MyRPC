package com.cxy.version3.common;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RPCResponse implements Serializable {
	private int code;
	private String message;
	private Object data;
	
	public static RPCResponse success(Object data){
		return RPCResponse.builder().code(200).data(data).build();
	}
	
	public static RPCResponse failed(){
		return RPCResponse.builder().code(500).message("服务器错误").build();
	}
}
