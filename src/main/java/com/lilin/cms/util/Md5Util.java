package com.lilin.cms.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {
    private static String salt="qazwsx1234";
	public static String encode(String password){
	
		return DigestUtils.md5Hex(password+salt);
	}
	public static void main(String[] args) {
		Md5Util.encode("123456");
		Md5Util.encode("1");
		Md5Util.encode("1");
	}
}
