package com.aironi.concurrency.example.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {
	@Autowired
	private RedisClient redisClient;
	
	@GetMapping("/set")
	public String set(String k, String v) {
		redisClient.set(k, v);
		return "success";
	}
	@GetMapping("/get")
	public String get(String k) {
		return redisClient.get(k);
	}
}
