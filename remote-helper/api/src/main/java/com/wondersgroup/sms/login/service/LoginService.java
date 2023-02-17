package com.wondersgroup.sms.login.service;

import com.wondersgroup.sms.user.bean.SmsUser;
import tw.ecosystem.reindeer.web.Result;


public interface LoginService {
    Result login(SmsUser user);
}
