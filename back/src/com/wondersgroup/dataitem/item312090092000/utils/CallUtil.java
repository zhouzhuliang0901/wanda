package com.wondersgroup.dataitem.item312090092000.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.log.Log;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.common.utils.PooledHttpUitl;
import com.wondersgroup.dataitem.item312090092000.bean.Message;

public class CallUtil {
	
	public static final String ENCODING = "UTF-8";
	
	public static final byte SIGNED = 1 << 0;
	
	protected static final int BUFFER_SIZE = 512;
	
    public static final int SIGNLEN = 256;
    
    public static final String privateKeyString = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDDNIuipD/w9dj4Pby9pXOzaCXEZT8V83tJIKilJqVKrIthXRHkEZIVMsPm9Gys8HA8mO7GAPCkGu2EdDMDnnzPJbQ1wO+Uny42TW6/WOFLEiNdDJ2BKoGYgQirB3ROOhuSQWSa+KbchG6iJOhOopkTw2tnOf0R2ZyOqhVwC5R/EZeseU1uUn9GsJXOoT0S8Z2+8AgavCPicDrlP5/3cjpeGCLsW0aQVN93SSscKCwvXxE5QpiYOkXj5a6s5dcjebKP/JKJzHj84GB2dLDLoJBoZ5VoqoGw8rqHHGf2m/vNCPjYA1mUTsZF8Cv7HZfEjp0ZeiO11ecb5Z2fQALwdWDVAgMBAAECggEBAJ8uABf2nHWf5Pg5T+nOO5U2/mUyssjxrb0qNpvF8c/8+APDY015zogex4VUGDLGNqATjdJE/fa0Rq9WGit/WAOLmXKkDDpDu+loJ0E99ynCdrfwC5hC0z9LKYheIceQtt8DGEkPLvJvw8/RDObizLVDDBg3P1kI/ROROr4Nk2AxybXuMGoQjjsMrq08kplSOK7wHqAyPPveXzai++XThbR0ycdnkTIyaVATgu1eIT7pcbRzz58R8ka0rhI2WAFOb08f0im31s5tsMZrMandvTzk6CT9r3pkC3vkNQpuLgNPVf/go4Tc104KmydpDXGkqR4akaj6mMy0vxQ0UokFB5kCgYEA5C5X3T9j6e91oyL+cclHDJihpG0fn5rK/ctiuFOuO8wby/dLSnK8UgvFbBEsqCzrbxcBURyoH59ysXprr01JfnSnY7r7yd2J/6JQr7dDkw8i13A28YGbkjZvHlWeRjfeZ+WnZAepaLTW/qUiKxm20fxJhSrlnscuUG3vdLpWTVMCgYEA2wECkXlqYYMiG2gz1+Vr+kozTrR5+TMUSliTZp2R3yCR0qF507wqECcigliqYCn8rdxeIqHHqNsUFzam+0x3f59J8sdfAljFH3D+DjgIwynVumNNpqkNm9Y9Zv1v1HGgNU25pkrfzj0T9advM1MpcbcD/pu91TIwrXtCem5NrDcCgYEAvj6JUVUVx6i36vE1Ncd8LltfgcjdseOMVTfqsgZK4V5W2qLvKj4pFzrrOiPDmOAC11dV6Lu8zMjTBngsKjeFDCLUoQj3/yFveixpm2dvtr/K69aKaKcH/0rVMHRwBtlz3CoUewkMTNlh22Pw7gB9dewjnBzc35nv2m0eVbM6WzMCgYEAzC/Z5CZuv/HSZ/k0dPNrRBXQVm5AVs5/+X0yGPeKXJ1yOA7jlj0Wfq7uJtdiKnybIMKYZfOuQsnXpnkHIL+/npa2DIxEEd0QFOt1g25NBSFJKxmQ+XUewg/yecaLcGhsNaZ9FAU1O4WIyX3NWO4Ncv9u0wED8fV0mxqT9bBH7OsCgYA5tEOFsuX3qvM6ew83/nf2tayKI01aZYpbUarb6km1LHEjHGvYk4PECQsFhN+p8Awe1Y2u39AQGxeLL2p0kb9fd9ZBPX44RWX4ABQZGUbvoUMgcJsqmJ9w9eqC4urCwDajxBiNtOGiaPODH5PtSM2I/sIUvXDJyRrusFI/jfEwmA==";
    public static final String publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwzSLoqQ/8PXY+D28vaVzs2glxGU/FfN7SSCopSalSqyLYV0R5BGSFTLD5vRsrPBwPJjuxgDwpBrthHQzA558zyW0NcDvlJ8uNk1uv1jhSxIjXQydgSqBmIEIqwd0TjobkkFkmvim3IRuoiToTqKZE8NrZzn9EdmcjqoVcAuUfxGXrHlNblJ/RrCVzqE9EvGdvvAIGrwj4nA65T+f93I6Xhgi7FtGkFTfd0krHCgsL18ROUKYmDpF4+WurOXXI3myj/ySicx4/OBgdnSwy6CQaGeVaKqBsPK6hxxn9pv7zQj42ANZlE7GRfAr+x2XxI6dGXojtdXnG+Wdn0AC8HVg1QIDAQAB";

    public static PrivateKey privateKey = null;
    public static PublicKey publicKey = null;


    public static final String dsj_sign_privateKeyString = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDaYETnNlKcYnaLKUIlWH1ZVxGtCZO+uQShc6omfKFEm0myPH79u6S20wHU+zIuzobZI4tnVnqxJ0M9LdW5Xwbx0ml2xsqvodZ+7iSd5ieMY8h15tRNBv3lmUpPmsdLg7SZkg0R+VSXprybluu/aMeX8r3emO1RCygqQhm8wKqNKPU/yP3rSf5sL9t3V95b6zlbbWjWpGcL/RwNJMQ2CjUDe1j5HIiMmGUa8krVDD4XCoJFioycegI2ZPht91jfnTjZsY8RLHN1YBfqbKNka7Ahly80MmRfygnmQ8kOcTY6iQcb3Hz+J8/ZwxHNqkQ9EZzWGB/N6FNkczf41JhAQbLNAgMBAAECggEAeguBlsLqapIw9Fdrq1mIPK0HqPCITPg0WVM+TeIrcx9ikaVzx7TeOns1M+XrxvMPtD1ERM21JlruHjwGjWJJjE7vOD2ju4K0UR4hAspFYhXGS/ZkwmPc9Du2p0S2i0KhZY6VlW0GVPcPw9kWClSx7/7vFH/dagTVPsDQh4gaCWdJU8AMclOi+uMhfC4FKomjHl5+5RDBuvFWRc71Rt87Efxo6H0RKhrzEs8c2q0bc707SAkkgBrs9Qgk8Cb1ZgqofeksU+QUN/bd32vfDmhcr5Vessog+3vnvxLZHoF6DtKd5gYxKMdK+MmdYIMVU6yui2RJ8qDg4nCB0WADU8Ab4QKBgQDvOFLUyauQ+5hoasv+jiCPd4VGGbsCEONlZ7TGbKuR6RMaSluCvvsbUggxWI+M3LgjGvvB3bZfE/m6mf8P/jIql1H0fNS2n908Iiq/VYgSSH/4KNl57ni1fJr1v8bqdHRtUOuTlIN7GgGIQHaxkJC8aDOFg7bFALgxwb1UuzXwKQKBgQDpsaZ73nUTgPlTec7NV01VHbRkN669svvKWSknRQ6jGDrrYkiKy79YoceU1E8UJkrbpvc8zZmE0JfyH7xG6qE6BdVzOMk3aA6QOZzpAv2C95zn6NItFs5XFTPgtd+LTFIdwTYSBWsFFyzQ7uccOf1Hc8PlosrLdeZ0WQEojmwyBQKBgE1uQLlpkaP7HAjoUKFTpcxqVnQrTfHMP56KI+R6NkqjU7CV0usOID9mnQpD7SseImgSitzkg+xgjdvNjtbBY8GPe1KUuVFni3e0Y3vNFp88YleT+VjTTLUJ4SqcaaloCmt73tTFJI3LDCXrH8A31/F0FQEpchZwNi681CjB9CKJAoGBANu9qAYhj4TfaZIMn2CGDrlSTSVPFohZnFkQrZm3XaQOTJi4ue2I2EvHE5vmZtVTjGAuB5ys1wYD/lw1axxj02ZzxdmqM8pdlhQXmGuh3C8iFfAbBEZRg4Ru83EAk0qwYSaJXwZjAdavi/yKJ9Zq0LY3LKTpBjd6jVApwOUZxrLhAoGAdfY4V4ePfb4ICCuZtqi+515HA7bFooskO6aqam8ehgG1ouThCk6KjDu/mKdgUCkSo9f/2FPvwfBrOrrSZTEoymdxrtEMtN7VqIBVc3kkLQiFolTWWhC3gyQgLIvrKs9SbJ8KDE5cGcA3r6zIv0HHZ719bn+zp+82C9lmL3ARzxU=";
    public static final String dsj_sign_publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2mBE5zZSnGJ2iylCJVh9WVcRrQmTvrkEoXOqJnyhRJtJsjx+/bukttMB1PsyLs6G2SOLZ1Z6sSdDPS3VuV8G8dJpdsbKr6HWfu4kneYnjGPIdebUTQb95ZlKT5rHS4O0mZINEflUl6a8m5brv2jHl/K93pjtUQsoKkIZvMCqjSj1P8j960n+bC/bd1feW+s5W21o1qRnC/0cDSTENgo1A3tY+RyIjJhlGvJK1Qw+FwqCRYqMnHoCNmT4bfdY35042bGPESxzdWAX6myjZGuwIZcvNDJkX8oJ5kPJDnE2OokHG9x8/ifP2cMRzapEPRGc1hgfzehTZHM3+NSYQEGyzQIDAQAB";
    public static final String dsj_enc_privateKeyString = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDfdCv6VpNuX9vvD9rx1CE5Yq+717TJehDQvvFF4i8Slk4zr13djfsjTExd2O92s8UwE2e76YZBZOOg5jVNuyYEgj/J6bVFJo+DfxfIm5J1m/8eMGX9h58/9tiQyKl8VwmMTkbAdaQmqPnsx9oX7gqPde40Vt3vOEFpJduJy0x9mx5sUEN86RT4vqXaEtcQWNT2E8eT17Ahy0pWrE8mJ5OLWdRP8DRv1R1nawIGSK7UXuMViub7mVtdhm3IO1P6qh76wiNRrjT1EUD8VbggAR9XWkGNPUJUAQrp96gc0tkgFR689Y+zNNbZAHxoiTOxp9wlM73I5wj/VSYdDrBEmuOnAgMBAAECggEBAIfOnFfmvG4NtqbB9Sia66Jr9zBsNkVpecTU9+CoSkG6tka1Y5AfFa5rVdKH4Wo8cjD46kgL8vvVrH8Cywy2HLIK02gNMY2YlQqveK4E12vBZrlbYvFGtyPyC8dbHZ8javsqK9gW57BTbKpbAJMgqqxsWVzYGsSkZSTZzGcf9xoyxPhXs1+bMFsGFQGnezdjGIE/D8qnsnX9u/joof/UxPNh6VoYMNIi21rVvArNaqsVwi7pRgF+NJmiyqSUj/PTgoG+bJ0WKBJv+fuWqLhzkVgp6PNc6NL03tS000xHEGrIXVKyEANp+psWUJddj/BoIdN8Su3npsTmgqFH40drPekCgYEA8g6TKmNy88RbfHXqpaMWfmgotIwFPnp+Qpy8a07DZvm4a1ma6txhi3WHBXCsNAbqN7E8WKj9U3V5bnU17VwkqwSAsRurZwJPEEeWF1G5yQ6K8M8+LZ09eF/iMj1U1ZEGaTM2rPNdjB2bV23MC3H0xpWh0b6A4wz92nL74Rm7zmMCgYEA7FNFXKqJufHRiPb4KEgj60hv5Ci9IXoNbwhCy2IQqTqe3dcK0qvoqcxMLZTjcgAk2vljYWX8axdguNxrY9PMqEO1sRjvnKre2khr0OmNvCgTHwN2RXOvilQFVAiL7JodErmZihcTqXy0g/SgLNt3tWtiZaDV8BVi8CsxEjUHhu0CgYEArHB1ptzyJ3bbNe9LGnT0sVjCptyZX8kj6ZB/KnMSAWolV1SeaHJXi2JOrjF2fBnxAXvh4ceBsNMfjvExWccq2Cp9A68SjG+4o4BcDIDMOc+Ca8s4IIbEI90q51+J8BtA1fEGIm+9VPRCliZCFmU4W8B9Z06vwxCeR+FiTxpmEMECgYArCZMlKA2C5m3xUoCo3VJlQ8DUvTKMSSzvHoMDhO1h1EIgRkw7R4FMgt8Qof+1wB72HdqiXy9T1WMQ51tLR8QKlKIfCVqBnoCNIgk4d4APVwD8ceX1Cfda6qg5wICy6vIGefRjOIalJAe/zCnrYWBwkojN1zd+zHZDJKqAFVY0lQKBgQDITLjRD1c1a6IT5BHKrcvySv1oZgCSBRWfIzuVdspO9Z+k1YGEdqfBomte1jIim00/XDpQXlUcvce7nh4dEnY4YoD5Pyx+ho2JWAs4cJQTbx6Ls29xMTTwzIpnw9TziUJnsAlFyBZ6xeQCbW8l/TXitMBXQWb0a+3pYRvUu3bBUg==";
    public static final String dsj_enc_publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA33Qr+laTbl/b7w/a8dQhOWKvu9e0yXoQ0L7xReIvEpZOM69d3Y37I0xMXdjvdrPFMBNnu+mGQWTjoOY1TbsmBII/yem1RSaPg38XyJuSdZv/HjBl/YefP/bYkMipfFcJjE5GwHWkJqj57MfaF+4Kj3XuNFbd7zhBaSXbictMfZsebFBDfOkU+L6l2hLXEFjU9hPHk9ewIctKVqxPJieTi1nUT/A0b9UdZ2sCBkiu1F7jFYrm+5lbXYZtyDtT+qoe+sIjUa409RFA/FW4IAEfV1pBjT1CVAEK6feoHNLZIBUevPWPszTW2QB8aIkzsafcJTO9yOcI/1UmHQ6wRJrjpwIDAQAB";

    //人社
    public static PrivateKey rs_privateKey = null;
    public static PublicKey rs_publicKey = null;
    //大数据签名验签
    public static PrivateKey dsj_sign_privateKey = null;
    public static PublicKey dsj_sign_publicKey = null;
    //大数据加解密
    public static PrivateKey dsj_enc_privateKey = null;
    public static PublicKey dsj_enc_publicKey = null;

    static {
        privateKey = RsaUtil.parsePrivateKey(privateKeyString);
        publicKey = RsaUtil.parsePublicKey(publicKeyString);
        rs_privateKey = RsaUtil.parsePrivateKey(privateKeyString);
        rs_publicKey = RsaUtil.parsePublicKey(publicKeyString);
        dsj_sign_privateKey = RsaUtil.parsePrivateKey(dsj_sign_privateKeyString);
        dsj_sign_publicKey = RsaUtil.parsePublicKey(dsj_sign_publicKeyString);
        dsj_enc_privateKey = RsaUtil.parsePrivateKey(dsj_enc_privateKeyString);
        dsj_enc_publicKey = RsaUtil.parsePublicKey(dsj_enc_publicKeyString);
    }
    
    public static Message callWithHttp(Message request, Map<String, String> head, String contentType) {
        CloseableHttpResponse httpResponse = null;
        InputStream is = null;
        BufferedReader reader = null;
        StringBuilder responseStr = new StringBuilder();
        try {
            // 接口调用
    		CloseableHttpClient client = PooledHttpUitl.closeableHttpClient;
    		String url = RdConfig.get("reindeer.huidao.url."+RdConfig.get("reindeer.huidao.environment"));
            HttpPost httpPost = new HttpPost(url);
            for (Map.Entry<String, String> entry : head.entrySet()) {
                System.out.println(entry.getKey() + "：" + entry.getValue());
                httpPost.addHeader(entry.getKey() , entry.getValue());
            }
            httpPost.setHeader("Content-type", contentType);
        	
            String requestString = Base64.encodeBase64String(write(request));
            httpPost.setEntity(new StringEntity(requestString, ENCODING));
            if(client == null){
        		if(url.startsWith("https://")){
        			client= HttpUtil.getHttpsClient();
        		} else {
        			client = HttpClients.createDefault();
        		}
            }
            httpResponse = client.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                is = entity.getContent();
                reader = new BufferedReader(new InputStreamReader(is, ENCODING));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    responseStr.append(line);
                }
            }
            // 获取响应内容
            Message response = read(Base64.decodeBase64(responseStr.toString()));
            return response;
        } catch (Exception e) {
        	Log.debug(responseStr.toString());
        	Log.debug(e);
        } finally {
            try {
            	if(httpResponse != null){
            		// 释放链接
            		httpResponse.close();
            	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return null;
    }
    
    public static byte[] write(Message message) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeShort(message.headbytes.length);
            dataOutputStream.writeInt(message.bodybytes.length);
            dataOutputStream.writeByte(message.flag);
            dataOutputStream.write(message.headbytes);
            dataOutputStream.write(message.bodybytes);
            if ((message.flag & SIGNED) == SIGNED) {
                dataOutputStream.write(message.signbytes);
            }
            dataOutputStream.flush();
        } finally {
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    public static Message read(byte[] request) throws IOException {
        Message message = null;
        DataInputStream dataInputStream = null;
        try {
            message = new Message();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(request);
            dataInputStream = new DataInputStream(byteArrayInputStream);

            short headlen = dataInputStream.readShort();
            int bodylen = dataInputStream.readInt();
            byte flag = dataInputStream.readByte();

            message.flag = flag;
            message.headbytes = readFully(dataInputStream, headlen);
            message.bodybytes = readFully(dataInputStream, bodylen);

            if ((flag & SIGNED) == SIGNED) {
                message.signbytes = readFully(dataInputStream, SIGNLEN);
            }
        } finally {
            if (dataInputStream != null) {
                dataInputStream.close();
            }
        }
        return message;
    }
    
    private static byte[] readFully(DataInputStream inputStream, int len) throws IOException {
        ByteArrayOutputStream baos = null;
        DataOutputStream dataOutputStream = null;
        try {
            baos = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(baos);

            int total = 0;
            int readbytes;
            byte[] buffer = new byte[BUFFER_SIZE];

            int max = Math.min(len, BUFFER_SIZE); // 当次读取最大字节

            while ((readbytes = inputStream.read(buffer, 0, max)) > 0) {
                dataOutputStream.write(buffer, 0, readbytes);
                total += readbytes;
                if (total >= len) {
                    break;
                }
                max = Math.min(BUFFER_SIZE, len - total);
            }
        } finally {
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
        }
        return baos.toByteArray();
    }
}
