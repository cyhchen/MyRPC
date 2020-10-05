package com.cxy.version6.register;

import java.util.List;
import java.util.Random;

public class RandomBalance implements LoadBalance {
	@Override
	public String balance(List<String> addresses){
		Random random = new Random();
		int num = random.nextInt(addresses.size());
		System.out.println("选择第"+num+"个服务器");
		return addresses.get(num);
	}
}
