package com.wondersgroup.selfapi.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

@Component
public class SendCancelReservationMsg {
	private ExecutorService executorService = Executors.newFixedThreadPool(20);

	public String sendCancelMsg(String ts, String site_code, String appo_num) {
		executorService.execute(new SendCancelReservationThreadUnit(ts,
				site_code, appo_num));
		return "true";
	}

	public String sendPTCancelMsg(String ts, String site_code, String appo_num) {
		executorService.execute(new SendCancelPTReservationThreadUnit(ts,
				site_code, appo_num));
		return "true";
	}

}
