package com.wondersgroup.collect.service.impl;

import java.sql.Timestamp;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.ecosystem.reindeer.web.HttpReqRes;
import tw.tool.helper.LogHelper;
import wfc.service.config.Config;

import com.wondersgroup.collect.service.CollectService;
import com.wondersgroup.infopub.bean.InfopubAttachment;
import com.wondersgroup.infopub.bean.InfopubDeviceInfo;
import com.wondersgroup.infopub.bean.InfopubDeviceLog;
import com.wondersgroup.infopub.bean.InfopubPublishExt;
import com.wondersgroup.infopub.dao.InfopubAttachmentDaoExt;
import com.wondersgroup.infopub.dao.InfopubDeviceInfoDaoExt;
import com.wondersgroup.infopub.dao.InfopubDeviceLogDaoExt;
import com.wondersgroup.infopub.dao.InfopubPublishDaoExt;

@Service
@Transactional
public class CollectServiceImpl implements CollectService {

	@Autowired
	private InfopubDeviceInfoDaoExt infopubDeviceInfoDaoExt;
	@Autowired
	private InfopubDeviceLogDaoExt infopubDeviceLogDaoExt;
	@Autowired
	private InfopubAttachmentDaoExt infopubAttachmentDaoExt;
	@Autowired
	private InfopubPublishDaoExt infopubPublishDaoExt;

	/**
	 * 获取设备提交的数据
	 */
	@Override
	public void post(HttpReqRes httpReqRes) {
		System.out.println(httpReqRes.getJsonStr());
		JSONObject jsonObject = new JSONObject();
		jsonObject = httpReqRes.getJson();
		LogHelper.info("jsonObject" + jsonObject.toString());
		String macId = jsonObject.getString("macid");
		String source = jsonObject.getString("source");
		if (macId != null && !StringUtils.trimToEmpty(macId).isEmpty()) {
			InfopubDeviceInfo infopubDeviceInfo = infopubDeviceInfoDaoExt
					.getByMacId(macId);
			if (infopubDeviceInfo == null) {
				infopubDeviceInfo = new InfopubDeviceInfo();
				infopubDeviceInfo.setStDeviceId(UUID.randomUUID().toString());
				infopubDeviceInfo.setStTypeId(StringUtils.EMPTY);
				infopubDeviceInfo.setStUserId(StringUtils.EMPTY);
				infopubDeviceInfo.setStDeviceIp(httpReqRes.getRequest()
						.getRemoteAddr());
				infopubDeviceInfo.setStDeviceMac(macId);
				infopubDeviceInfo.setStChannel(source);
				infopubDeviceInfo.setDtCreate(new Timestamp(System
						.currentTimeMillis()));
				infopubDeviceInfo.setDtUpdate(new Timestamp(System
						.currentTimeMillis()));
				infopubDeviceInfoDaoExt.add(infopubDeviceInfo);
			}
			InfopubDeviceLog deviceLog = new InfopubDeviceLog();
			deviceLog.setStAction("设备发送消息");
			deviceLog.setStDeviceId(infopubDeviceInfo.getStDeviceId());
			deviceLog.setStMsg(jsonObject.toString());
			deviceLog.setStOperand("监控系统");
			deviceLog.setStOperator(macId);
			infopubDeviceLogDaoExt.add(deviceLog);
		}
	}

	/**
	 * 截屏操作
	 */
	@Override
	public void snapshots(HttpReqRes httpReqRes) {
		String macId = httpReqRes.getParameter("macid");
		if (macId != null && !StringUtils.trimToEmpty(macId).isEmpty()) {
			InfopubDeviceInfo infopubDeviceInfo = infopubDeviceInfoDaoExt
					.getByMacId(macId);
			if (infopubDeviceInfo != null) {
				Timestamp nowTime = new Timestamp(System.currentTimeMillis());
				FileItem item = httpReqRes.getFileItem("snapshots");
				InfopubAttachment attachment = new InfopubAttachment();
				attachment.setStAttachId(UUID.randomUUID().toString());
				attachment
						.setStLinkTable(InfopubDeviceInfo.INFOPUB_DEVICE_INFO);
				attachment.setStLinkId(infopubDeviceInfo.getStDeviceId());
				attachment.setStAttachType(item.getContentType());
				attachment.setBlContent(item.get());
				attachment.setStFilename(item.getFieldName());
				attachment.setStFileSize(String.valueOf(item.getSize()));
				attachment.setDtCreate(nowTime);
				attachment.setDtUpdate(nowTime);
				infopubAttachmentDaoExt.addWithFile(attachment);
			}
		}
	}

	/**
	 * 发布跳转
	 */
	@Override
	public String redirect(HttpReqRes httpReqRes) {
		String query = httpReqRes.getRequest().getQueryString();
		if (query != null && !StringUtils.trimToEmpty(query).isEmpty()) {
			String[] msg = query.split("=");
			String mac = msg[1];
			if (mac != null && !StringUtils.trimToEmpty(mac).isEmpty()) {
				InfopubDeviceInfo infopubDeviceInfo = infopubDeviceInfoDaoExt
						.getByMacId(mac);
				if (infopubDeviceInfo != null) {
					InfopubPublishExt infopubPublishExt = infopubPublishDaoExt
							.getPublish(infopubDeviceInfo.getStDeviceId());
					if (infopubPublishExt != null) {
						return infopubPublishExt.getClContent();
					}
				}
			}
		}
		return Config.get("infopub.error.redirect");
	}
}
