package com.springcourse.service.util;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class HashUtilTests {
	
	@Test
	public void getSecureHashTest() {
		String hash = HashUtil.getSecureHash("123");
		System.out.println(hash);
		assertThat(hash.length()).isEqualTo(64);
	}
}
