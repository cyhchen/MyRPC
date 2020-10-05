package com.cxy.version4.client;

import com.cxy.version4.common.RPCRequest;
import com.cxy.version4.common.RPCResponse;

public interface RPCClient {
	RPCResponse sendRequest(RPCRequest request);
}
