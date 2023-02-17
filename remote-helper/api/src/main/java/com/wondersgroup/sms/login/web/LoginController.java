package com.wondersgroup.sms.login.web;

import com.wondersgroup.sms.login.service.LoginService;
import com.wondersgroup.sms.user.bean.SmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.ecosystem.reindeer.web.Result;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/sms/login/login")
    public Result login(SmsUser account) {
        return loginService.login(account);
    }
}
