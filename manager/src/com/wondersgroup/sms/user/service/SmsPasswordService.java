package com.wondersgroup.sms.user.service;

import com.wondersgroup.app.bean.Oauth2Client;
import com.wondersgroup.sms.user.bean.SmsUser;

public interface SmsPasswordService {

	void encryptPassword(SmsUser user);

	void encryptPassword(Oauth2Client newOauth2Client);
}
