package com.wondersgroup.sms.login.service.impl;

import com.wondersgroup.sms.login.service.LoginService;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.dao.SmsUserDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reindeer.utils.JwtUtil;
import tw.ecosystem.reindeer.web.Result;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private SmsUserDao smsUserDao;


    @Override
    public Result login(SmsUser user) {
        String stLoginName = user.getStLoginName();
        String stPassword = user.getStPassword();
        if (StringUtils.isBlank(stLoginName) || StringUtils.isBlank(stPassword)) {
            return Result.getResult().failed().setMsg("账号密码不能为空");
        }
        List<SmsUser> users = smsUserDao.queryByUsername(stLoginName);
        if (users != null && users.size() > 0) {
            SmsUser smsUser = users.get(0);
            if (stPassword.equals(smsUser.getStPassword())) {
                return Result.getResult().success().setMsg("登录成功").setData(JwtUtil.sign(smsUser));
            } else {
                return Result.getResult().failed().setMsg("密码错误");
            }
        } else {
            return Result.getResult().failed().setMsg("用户名不存在");
        }


    }
}
