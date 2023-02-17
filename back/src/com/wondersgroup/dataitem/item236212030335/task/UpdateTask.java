package com.wondersgroup.dataitem.item236212030335.task;

import java.sql.Timestamp;

import com.wondersgroup.common.operator.connector.ZeroOp;
import com.wondersgroup.dataitem.item236212030335.utils.ResidenceLicenseUtil;

/**
 * 每天凌晨24点10分更新加解密密钥、随机字串和登陆令牌
 * @author wanda
 *
 */
public class UpdateTask implements ZeroOp{

	@Override
	public void execute(Timestamp current) {
		ResidenceLicenseUtil.update();
	}
}
