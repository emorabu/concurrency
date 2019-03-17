package com.aironi.concurrency.example.atomic;

import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aironi.concurrency.annotations.ThreadSafe;

@ThreadSafe
public class AtomicExample4 {
	private static final Logger LOGGER = LoggerFactory.getLogger(AtomicExample4.class);
	private static AtomicReference<Integer> count = new AtomicReference<Integer>(0);

	public static void main(String[] args) {
		count.compareAndSet(0, 2); // 2
		count.compareAndSet(0, 1); // 不执行
		count.compareAndSet(1, 3); // 不执行
		count.compareAndSet(2, 4); // 4
		count.compareAndSet(3, 5); // 不执行
		LOGGER.info("count: " + count.get()); // 4
	}
}
