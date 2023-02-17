package com.wondersgroup.base.utils;

import com.wondersgroup.ssologin.SsoHelper;

public class SsoLoginTest {

	public static void main(String[] args) {
		// 源系统加密字符串
		String desStr = SsoHelper.getSsoDes("admin");
		System.out.println(desStr);
		// 原系统加密字符串 并且经过url编码
		String encDesStr = SsoHelper.getSafeSsoDes("admin");
		System.out.println(encDesStr);
		// 源系统加密字符串（安全得base64编码）
		/*
		 * String desSafeStr = SsoHelper.getSafeSsoDes("coral");
		 * System.out.println(desSafeStr);
		 */
		// 目标系统解密字符串
		System.out.println(SsoHelper.getUser(desStr));
		// 目标系统解密字符串 并且经过url解码
		System.out.println(SsoHelper.getDecoderUser(encDesStr));
		// 目标系统解密字符串（安全得base64编码）
		/* System.out.println(SsoHelper.getSafeUser(desSafeStr)); */

	}
}
