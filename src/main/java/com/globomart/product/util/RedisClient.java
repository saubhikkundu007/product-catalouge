package com.globomart.product.util;

import redis.clients.jedis.Jedis;

public class RedisClient {
	private static Jedis jedis;
	
	private RedisClient(){
	}
	
	public static Jedis getInstance(){
		if(jedis == null){
			jedis = new Jedis();
		}
		return jedis;
	}
	
	
	
}
