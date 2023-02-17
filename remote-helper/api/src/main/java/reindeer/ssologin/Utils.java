package reindeer.ssologin;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

class Utils {

	protected static String decode(String desCode, String key) throws Exception {
		byte[] desByte = Base64.decodeBase64(desCode);
		DESKeySpec keySpec = new DESKeySpec(key.getBytes("ASCII"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		Cipher objCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		objCipher.init(Cipher.DECRYPT_MODE, secretKey);
		return new String(objCipher.doFinal(desByte), "UTF-8").trim();
	}

	protected static String encode(String srcCode, String key) throws Exception {
		DESKeySpec keySpec = new DESKeySpec(key.getBytes("ASCII"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		Cipher objCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		objCipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] desByte = objCipher.doFinal(srcCode.getBytes("UTF-8"));
		return Base64.encodeBase64String(desByte);
	}

	protected static String safeEncode(String srcCode, String key)
			throws Exception {
		DESKeySpec keySpec = new DESKeySpec(key.getBytes("ASCII"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(keySpec);
		Cipher objCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		objCipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] desByte = objCipher.doFinal(srcCode.getBytes("UTF-8"));
		return Base64.encodeBase64URLSafeString(desByte);
	}

	protected static String safeUrlBase64Encode(byte[] data) {
		String encodeBase64 = Base64.encodeBase64String(data);
		String safeBase64Str = encodeBase64.replace('+', '-');
		safeBase64Str = safeBase64Str.replace('/', '_');
		safeBase64Str = safeBase64Str.replaceAll("=", "");
		return safeBase64Str;
	}

	protected static byte[] safeUrlBase64Decode(String safeBase64Str) {
		String base64Str = safeBase64Str.replace('-', '+');
		base64Str = base64Str.replace('_', '/');
		int mod4 = base64Str.length() % 4;
		if (mod4 > 0) {
			base64Str = base64Str + "====".substring(mod4);
		}
		return Base64.decodeBase64(safeBase64Str);
	}
}
