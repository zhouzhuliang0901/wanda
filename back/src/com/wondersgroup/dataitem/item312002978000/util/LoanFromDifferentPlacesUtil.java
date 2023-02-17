package com.wondersgroup.dataitem.item312002978000.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class LoanFromDifferentPlacesUtil {
	
	public static String signParams(Map<String, String> paramsMap){
		String sign = "";
		Set<String> keySet = paramsMap.keySet();
		List<String> list = new ArrayList<String>();
		for(Iterator<String> ketIt =  keySet.iterator();ketIt.hasNext();){
			String key = ketIt.next();
			list.add(key);
		}
		List<char[]> sortList = sortStringListByASCII(list);
		String signString = "";
		for(char[] ch : sortList){
			String key = String.valueOf(ch);
			String value = paramsMap.get(key);
			if(StringUtils.isNotEmpty(value)){
				signString += key+"="+value+"&";
			}
		}
		// 测试：shgjjtxyjs
		signString += "key=shgjjtxyjs";
		sign = DigestUtils.md5Hex(signString).toLowerCase();
		return sign;
	}
	
	/**
	 * 将String数组按ASCII排序
	 * @param list
	 * @return
	 */
	public static List<char[]> sortStringListByASCII(List<String> list) {
        List<char[]> charList = new ArrayList<char[]>();
        // 1.把list数组中的每个字符串 转为字符数组
        for (int i = 0; i < list.size(); i++) {
            charList.add(list.get(i).toCharArray());
        }
        List<char[]> sortList = charList;
        // 2.循环数组listChar取出每一个进行冒泡排序比较
        for (int a = 0; a < charList.size(); a++) {
            for (int b = 0; b < charList.size(); b++) {
                char[] charA = charList.get(a);
                char[] charB = charList.get(b);
                // 相同的就不需要比较
                if (charA != charB) {
                    int size = 0;
                    // 可能会存在前面都一样的字符所有取字符数组长度小的 来进行循环比较 字符的ascll
                    if (charA.length > charB.length) {
                        size = charB.length;
                    } else {
                        size = charA.length;
                    }
                    // 循环比较赋值
                    for (int c = 0; c < size; c++) {
                        if ((int) charA[c] > (int) charB[c]) {
                            if (a < b) {
                            	sortList.set(b, charA);
                            	sortList.set(a, charB);
                                break;
                            }
                        } else if ((int) charA[c] == (int) charB[c]) {
 
                        } else if ((int) charA[c] < (int) charB[c]) {
                            break;
                        }
                    }
                }
            }
        }
        return sortList;
    }
	
	public static void main(String[] args) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("provinceName", "浙江省");
		paramsMap.put("cityName", "杭州市");
		paramsMap.put("centerName", "");
		String sign = signParams(paramsMap);
		System.out.println(sign);
	}
}
