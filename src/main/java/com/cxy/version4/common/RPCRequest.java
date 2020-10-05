package com.cxy.version4.common;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RPCRequest implements Serializable {
	private Class[] paramsTypes;
	private Object[] params;
	private String interfaceName;
	private String methodName;
	
}
