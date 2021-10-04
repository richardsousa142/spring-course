package com.springcourse.service.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {
	public static String getSecureHash(String password) {
		String encryptedPassword = DigestUtils.sha256Hex(password);
		return encryptedPassword;
	}
}
