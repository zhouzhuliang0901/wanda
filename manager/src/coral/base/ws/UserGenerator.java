package coral.base.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import coral.base.util.SecurityHelper;

public class UserGenerator {

	public static void main(String[] args) throws IOException {
		System.out.println("请输入用户名：");
		BufferedReader br1 = new BufferedReader(
				new InputStreamReader(System.in));
		String username = br1.readLine();
		System.out.println("请输入DES密钥：");
		BufferedReader br2 = new BufferedReader(
				new InputStreamReader(System.in));
		String hexStr = br2.readLine();
		SecurityHelper securityHelper = new SecurityHelper(hexStr);
		String password = SecurityHelper.byteToHexStr(securityHelper
				.encode(username.getBytes("UTF-8")));
		System.out.println("该用户口令为：" + password);
	}

}
