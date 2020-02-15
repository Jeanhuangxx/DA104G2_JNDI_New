package com.websocketchat.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<String> getHistoryMsg(String room) {
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		List<String> historyData = new LinkedList<String>();

		int i = jedis.llen(room).intValue();

		if (i > 10) {
			for (int j = i-5; j < i ; j++) {
//				System.out.println(jedis.lindex(room, j - 1));
				if(jedis.lindex(room, j)!=null)
				historyData.add(jedis.lindex(room, j));
			}
		} else {
			for (int j = 0; j <6 ; j++) {
//				System.out.println(jedis.lindex(room, j - 1));
				if(jedis.lindex(room, j - 1)!=null)
				historyData.add(jedis.lindex(room, j - 1));
			}
		}
		jedis.close();
		return historyData;
	}

	public static void saveChatMessage(String room, String message) {
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		jedis.rpush(room, message);

		jedis.close();
	}

}
