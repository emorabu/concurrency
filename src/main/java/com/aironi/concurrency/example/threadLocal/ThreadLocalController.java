package com.aironi.concurrency.example.threadLocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/thread-local")
public class ThreadLocalController {
	@ResponseBody
	@RequestMapping("/test")
	public long test() {
		return RequestHolder.getId();
	}
}
