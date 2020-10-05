package com.cxy.version6.client;

import com.cxy.version6.common.RPCRequest;
import com.cxy.version6.common.RPCResponse;

public interface RPCClient {
	RPCResponse sendRequest(RPCRequest request);
}
