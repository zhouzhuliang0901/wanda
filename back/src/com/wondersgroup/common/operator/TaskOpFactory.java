package com.wondersgroup.common.operator;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wfc.service.log.Log;
import com.wondersgroup.common.operator.connector.EightHoursOp;
import com.wondersgroup.common.operator.connector.NightOp;
import com.wondersgroup.common.operator.connector.OneMonthOp;
import com.wondersgroup.common.operator.connector.QuarterOp;
import com.wondersgroup.common.operator.connector.ZeroOp;

@Component(value = "taskOpFactory")
public class TaskOpFactory {

	@Autowired(required = false)
	private List<EightHoursOp> eightHoursOpList;
	
	@Autowired(required = false)
	private List<ZeroOp> zeroOpList;
	
	@Autowired(required = false)
	private List<OneMonthOp> OneMonthOpList;
	
	@Autowired(required = false)
	private List<QuarterOp> QuarterOpList;
	
	@Autowired(required = false)
	private List<NightOp> NightOpList;
	
	// 每8小时执行一次
	public void execEightHoursOp(Timestamp current) {
		if (eightHoursOpList != null) {
			for (EightHoursOp op : eightHoursOpList) {
				try {
					op.execute(current);
				} catch (Exception e) {
					Log.error(e);
				}
			}
		}
	}
	
	// 每天凌晨24点1分执行
	public void execZeroOp(Timestamp current) {
		if (zeroOpList != null) {
			for (ZeroOp op : zeroOpList) {
				try {
					op.execute(current);
				} catch (Exception e) {
					Log.error(e);
				}
			}
		}
	}
	
	// 每月最后一天晚11点执行
	public void execOneMonthOp(Timestamp current) {
		if (OneMonthOpList != null) {
			for (OneMonthOp op : OneMonthOpList) {
				try {
					op.execute(current);
				} catch (Exception e) {
					Log.error(e);
				}
			}
		}
	}
	
	//15分钟执行一次
	public void execQuarterOp(Timestamp current) {
		if (QuarterOpList != null) {
			for (QuarterOp op : QuarterOpList) {
				try {
					op.execute(current);
				} catch (Exception e) {
					Log.error(e);
				}
			}
		}
	}
	
	//晚上更新
	public void execNightOp(Timestamp current) {
		if (NightOpList != null) {
			for (NightOp op : NightOpList) {
				try {
					op.execute(current);
				} catch (Exception e) {
					Log.error(e);
				}
			}
		}
	}
}
