package com.wondersgroup.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessyCodeUtil {
	
	  private static boolean isChinese(char c) {
	        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
	                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
	                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
	                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
	                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
	                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
	            return true;
	        }
	        return false;
	    }
	    
	    public static boolean isMessyCode(String strName) {
	        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
	        Matcher m = p.matcher(strName);
	        String after = m.replaceAll("");
	        String temp = after.replaceAll("\\p{P}", "");
	        char[] ch = temp.trim().toCharArray();
	        float chLength = 0 ;
	        float count = 0;
	        for (int i = 0; i < ch.length; i++) {
	            char c = ch[i];
	            if (!Character.isLetterOrDigit(c)) {
				if (!isChinese(c)) {
	                    count = count + 1;
	                }
	                chLength++; 
	            }
	        }
	        float result = count / chLength ;
	        if (result > 0.4) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	    
	    public static Boolean isLessUseWord(String org) throws UnsupportedEncodingException {
	        return !org.equals(new String(org.getBytes("gb18030"),"gb2312"));
	    }
	    
	    public static void main(String[] args) throws UnsupportedEncodingException {
	    	String s = "鐜嬫垚";
	    	s = new String(s.getBytes("gb18030"),"utf-8");
	    	System.out.println(s);
	    }
}
