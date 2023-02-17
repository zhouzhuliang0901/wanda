package com.wondersgroup.selfapi.util;

import wfc.service.log.Log;

public class SendCancelReservationThreadUnit implements Runnable {

	private String ts;

	private String site_code;

	private String appo_num;

	public SendCancelReservationThreadUnit(String ts, String site_code, String appo_num) {
		this.ts = ts;
		this.site_code = site_code;
		this.appo_num = appo_num;
	}

	@Override
	public void run() {
		String result = CAUtils.send(ts, site_code, appo_num);
		Log.info("CA证书核销接口返回的数据结果" + result + "]");
	}
}
