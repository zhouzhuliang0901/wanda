package com.wondersgroup.assistant.util;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class ResultUtil {
    public String getNullRSuccess(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", "true");
        jsonObject.put("retmsg", "");
        return jsonObject.toJSONString();
    }
    public String getSuccess(List list){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", "true");
        jsonObject.put("retmsg", "");
        jsonObject.put("rtnList", list);
        return jsonObject.toJSONString();
    }

}
