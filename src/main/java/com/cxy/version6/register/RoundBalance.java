package com.cxy.version6.register;

import java.util.List;

public class RoundBalance implements LoadBalance {
	private int num = -1;
	@Override
	public String balance(List<String> addresses){
		num++;
		num %= addresses.size();
		System.out.println("选择第"+num+"个服务器");
		return addresses.get(num);
	}
}
