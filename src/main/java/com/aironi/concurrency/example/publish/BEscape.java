package com.aironi.concurrency.example.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aironi.concurrency.annotations.NotRecommend;
import com.aironi.concurrency.annotations.ThreadUnsafe;

@ThreadUnsafe
@NotRecommend
public class BEscape {
	private static final Logger LOGGER = LoggerFactory.getLogger(BEscape.class);
	private int canEscape= 0;

	public BEscape() {
		new InnerClass();
	}
	private class InnerClass{
		public InnerClass() {
			LOGGER.info("{}",BEscape.this.canEscape);
		}
	}
	
	public static void main(String[] args) {
		new BEscape(); // 0
	}
}
