package com.wondersgroup.business.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Decode {
	public static String decode(String str, String enc) { // remove null string
		 // 解码
		 try {
		  str = URLDecoder.decode(str, enc);
		 } catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
		 }

		 return str;
		}
}
