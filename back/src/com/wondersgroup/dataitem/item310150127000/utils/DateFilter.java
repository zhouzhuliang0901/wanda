package com.wondersgroup.dataitem.item310150127000.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class DateFilter {
	
	//过滤掉预约满的日期和节假日
	@SuppressWarnings("unchecked")
	public static  String dateFilter(String result) {
		
		JSONObject jsonObject = JSONObject.parseObject(result);
		String startDate = jsonObject.getString("startDate");
		String endDate = ""; 
		List<String> fullDayList = (List<String>)jsonObject.get("fullDayList");
		List<String> offDayList = (List<String>)jsonObject.get("offDayList");	
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//得到30天后日期
		Calendar calc =Calendar.getInstance();  
		
        try {  
            calc.setTime(sdf.parse(startDate));  
            calc.add(Calendar.DAY_OF_MONTH, 29);
            endDate = new SimpleDateFormat("yyyy-MM-dd").format(calc.getTime());
        } catch (Exception e1) {  
            e1.printStackTrace();  
        } 
        
		Date dBegin = null;  
	    Date dEnd = null; 
	    
		try {
		     dBegin = sdf.parse(startDate);  
		     dEnd = sdf.parse(endDate);  
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		List<Date> allDays = getDatesBetweenTwoDate(dBegin, dEnd);
		List<String> allDaysString = new ArrayList<String>();
		
		for(int i = 0 ; i < allDays.size() ; i++ )
		{
			String day = sdf.format(allDays.get(i));
			allDaysString.add(day);
		}
		
		for(int i = 0 ; i < allDaysString.size() ; i++) {
			for(int j = 0 ; j <fullDayList.size() ; j++){
				if(allDaysString.get(i).equals(fullDayList.get(j))){
					allDaysString.set(i,"");
				}
			}
			for(int k = 0 ; k <offDayList.size() ; k++){
				if(allDaysString.get(i).equals(offDayList.get(k))){
					allDaysString.set(i,"");
				}
			}
		}
		
		List<String> result1 = new ArrayList<String>();
		for(int i = 0 ; i < allDaysString.size() ; i++) {
			int j = 0;
			if(!allDaysString.get(i).equals("")){
				result1.add(j,allDaysString.get(i));
				j++;
			}
		}
		
		Collections.sort(result1);
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("date",result1);
		jsonObject2.put("fullDayList",fullDayList);
		jsonObject2.put("offDayList",offDayList);
		result = jsonObject2.toJSONString();
		return result; 
	}
	
	//获取起始时间和截止时间
	 public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {  
		List<Date> lDate = new ArrayList<Date>();  
		lDate.add(beginDate);// 把开始时间加入集合  
		Calendar cal = Calendar.getInstance();  
		// 使用给定的 Date 设置此 Calendar 的时间  
		cal.setTime(beginDate);  
		boolean bContinue = true;  
		while (bContinue) {  
		// 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
		cal.add(Calendar.DAY_OF_MONTH, 1);  
		// 测试此日期是否在指定日期之后  
			if (endDate.after(cal.getTime())) {  
				lDate.add(cal.getTime());  
			} else {  
				break;  
			}  
		}  
		lDate.add(endDate);// 把结束时间加入集合  
		return lDate;  
	}  

}
