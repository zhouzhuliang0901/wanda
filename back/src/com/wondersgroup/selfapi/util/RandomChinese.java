package com.wondersgroup.selfapi.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class RandomChinese {
	/**
     * 获取指定长度随机简体中文
     * @param len int
     * @return String
     */
	public static String getRandomJianHan(int len)
    {
        String ret="";
          for(int i=0;i<len;i++){
              String str = null;
              int hightPos, lowPos; // 定义高低位
              Random random = new Random();
              hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
              lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
              byte[] b = new byte[2];
              b[0] = (new Integer(hightPos).byteValue());
              b[1] = (new Integer(lowPos).byteValue());
              try
              {
                  str = new String(b, "GBk"); //转成中文
              }
              catch (UnsupportedEncodingException ex)
              {
                  ex.printStackTrace();
              }
               ret+=str;
          }
      return ret;
    }
	

	/**
     * 获取指定长度随机简体中文
     * @param len int
     * @return String
     */
	public static String getRandomJianHan2(int len){
		//汉字范围19968-40869
		int MIN_INDEX = 19968;
		int MAX_INDEX = 40869;
		int aa = (MIN_INDEX + MAX_INDEX)/2;
		int bb = (MAX_INDEX - MIN_INDEX)/2;
        String ret="";
          for(int i=0;i<len;i++){
        	  Random random = new Random();
        	  int num = aa + Math.abs(random.nextInt(bb));
              String str = String.valueOf((char)num);
              ret+=str;
          }
      return ret;
    }
}
