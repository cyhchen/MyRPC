package com.cxy.version5.client;

import com.cxy.version5.common.RPCRequest;
import com.cxy.version5.common.RPCResponse;

public interface RPCClient {
	RPCResponse sendRequest(RPCRequest request);
}
