package com.wondersgroup.dataitem.item391843927822.task;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wondersgroup.common.operator.connector.EightHoursOp;
import com.wondersgroup.dataitem.item391843927822.utils.TokenUtil;

@Component
public class UpdatetokenTask implements EightHoursOp{
	
	@Autowired
	private TokenUtil tokenUtil;

	@Override
	public void execute(Timestamp current) {
		tokenUtil.getToken();
	}

}
