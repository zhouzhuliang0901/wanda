package com.wondersgroup.sms.cache.service;

import java.math.*;
import java.sql.*;

import net.sf.json.JSONObject;

import com.wondersgroup.sms.cache.bean.SelmBigscreenCache;
import coral.base.util.RequestWrapper;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.facility.tool.autocode.PaginationArrayList;

/**
 * 大屏统计缓存表业务接口
 * 
 * @author scalffold
 * 
 */
public interface SelmBigscreenCacheService {

	JSONObject getCacheData(HttpReqRes httpReqRes);

}
