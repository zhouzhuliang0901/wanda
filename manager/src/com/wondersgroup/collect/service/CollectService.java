package com.wondersgroup.collect.service;

import org.springframework.stereotype.Service;

import tw.ecosystem.reindeer.web.HttpReqRes;

/**
 * 
 * @author guicb
 *
 */
@Service
public interface CollectService {

	/**
	 * 客户端提交的数据
	 * 
	 * @param httpReqRes
	 */
	void post(HttpReqRes httpReqRes);

	/**
	 * 截屏操作
	 * 
	 * @param httpReqRes
	 */
	void snapshots(HttpReqRes httpReqRes);

	/**
	 * 发布跳转
	 * 
	 * @param httpReqRes
	 * @return
	 */
	String redirect(HttpReqRes httpReqRes);
}
