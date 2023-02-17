package com.wondersgroup.infopub.service;



import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import tw.ecosystem.reindeer.web.HttpReqRes;


@Service
public interface InfoPubService {

	JSONObject getAlldeviceInfo(HttpReqRes httpReqRes);

	JSONObject getSelmQuery(HttpReqRes httpReqRes);

	JSONObject getAreaSelmQuery(HttpReqRes httpReqRes);

	JSONObject getAreadeviceInfo(HttpReqRes httpReqRes);

	JSONObject deviceInfoListMac(HttpReqRes httpReqRes);

	JSONObject selmQuertTop(HttpReqRes httpReqRes);

	JSONObject addresslistTypeDevice(HttpReqRes httpReqRes);

	JSONObject selmQuertNum(HttpReqRes httpReqRes);

	JSONObject selmQuertListTow(HttpReqRes httpReqRes);

	JSONObject areaSelmQuert(HttpReqRes httpReqRes);

	JSONObject areaSelmQuertTop(HttpReqRes httpReqRes);

	JSONObject streetDeviceList(HttpReqRes httpReqRes);

	JSONObject areaDeviceInfo(HttpReqRes httpReqRes);

	JSONObject itemAreaQuery(HttpReqRes httpReqRes);

	JSONObject itemStreetQuery(HttpReqRes httpReqRes);

	JSONObject streetSelmQuerTop(HttpReqRes httpReqRes);

	JSONObject itemPeNumber(HttpReqRes httpReqRes);

	JSONObject visitComItem(HttpReqRes httpReqRes);

	JSONObject itemPercentMonth(HttpReqRes httpReqRes);

	JSONObject areaThirtySelmQuery(HttpReqRes httpReqRes);

	JSONObject areaDeviceCount(HttpReqRes httpReqRes);

	JSONObject todaySelmQuery(HttpReqRes httpReqRes);

	JSONObject deviceAndHandCount(HttpReqRes httpReqRes);

	JSONObject itemOfMonth(HttpReqRes httpReqRes);

	JSONObject areaItemOfMonth(HttpReqRes httpReqRes);

	JSONObject socialCount(HttpReqRes httpReqRes);

	JSONObject areaItemCount(HttpReqRes httpReqRes);

	JSONObject realTimeHand(HttpReqRes httpReqRes);

	JSONObject govDeviceCount(HttpReqRes httpReqRes);

	
	
}
