/** 
 * @(#)StringUtils.java 2017-8-17
 * 
 * Copyright (c) 1995-2016 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */
package com.wondersgroup.dataitem.item310382044523.util;

public class StringUtils {
	public static boolean isEmpty(String str){
		if(str == null){
			return true;
		}
		if("".equals(str)){
			return true;
		}
		return false;
	}
}
