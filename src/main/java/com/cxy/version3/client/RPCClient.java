package com.cxy.version3.client;

import com.cxy.version3.common.RPCRequest;
import com.cxy.version3.common.RPCResponse;

public interface RPCClient {
	RPCResponse sendRequest(RPCRequest request);
}
