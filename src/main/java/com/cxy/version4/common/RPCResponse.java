package com.cxy.version4.common;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RPCResponse implements Serializable {
	private int code;
	private String message;
	private Class<?> dataType;
	private Object data;
	
	public static RPCResponse success(Object data){
		return RPCResponse.builder().code(200).data(data).dataType(data.getClass()).build();
	}
	
	public static RPCResponse failed(){
		return RPCResponse.builder().code(500).message("服务器错误").build();
	}
}
