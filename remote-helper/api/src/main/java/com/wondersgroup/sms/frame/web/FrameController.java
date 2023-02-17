package com.wondersgroup.sms.frame.web;

import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.sms.menu.service.SmsMenuService;
import com.wondersgroup.sms.menu.view.SmsMenuView;
import com.wondersgroup.sms.user.bean.SmsUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FrameController {
    @Autowired
    private SmsMenuService smsMenuService;

    @RequiresAuthentication
    @RequestMapping("/sms/frame/menu")
    public WdfResult dologin(SmsUser account) {
        SmsUser user =  (SmsUser) SecurityUtils.getSubject().getPrincipal();
        //List<SmsMenuView> list = smsMenuService.getSystemAllMenuTree();
        String userId = "";
        if(null != user){
            userId = user.getStUserId();
        }

        List<SmsMenuView> list = smsMenuService.getSystemMenuByUserId(userId);
        /*for(SmsMenuView emp : list){
            System.out.println("++"+emp);
        }*/
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("menuList", list);
        JSONObject json = new JSONObject(map);
        return WdfResult.getResult().success().setData(json);
    }
}
