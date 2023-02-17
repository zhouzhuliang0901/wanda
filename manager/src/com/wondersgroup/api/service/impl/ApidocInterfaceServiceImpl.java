package com.wondersgroup.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.tool.helper.Request2BeanHelper;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.wondersgroup.api.bean.ApidocInterface;
import com.wondersgroup.api.bean.ApidocModule;
import com.wondersgroup.api.dao.ApidocInterfaceDao;
import com.wondersgroup.api.dao.ApidocModInterDao;
import com.wondersgroup.api.dao.ApidocModuleDao;
import com.wondersgroup.api.service.ApidocInterfaceService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;

/**
 * 接口业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class ApidocInterfaceServiceImpl implements ApidocInterfaceService {

	/**
	 * 根据主键 {@link ApidocInterface#ST_INTERFACE_ID}获取接口
	 * 
	 * @param stInterfaceId
	 *            接口主键 {@link ApidocInterface#ST_INTERFACE_ID}
	 * @return 接口实例
	 */
	@Override
	public ApidocInterface get(String stInterfaceId) {
		if (StringUtils.trimToEmpty(stInterfaceId).isEmpty())
			throw new NullPointerException("Parameter stInterfaceId cannot be null.");
		return apidocInterfaceDao.get(stInterfaceId);
	}

	/**
	 * 查询接口列表（分页及查询功能）
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 接口列表
	 */
	@Override
	public PaginationArrayList<ApidocInterface> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = " order by NM_ORDER ";
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			String stModuleName = wrapper.getParameter("stModuleName");
			String stInterfaceName = wrapper.getParameter("stInterfaceName");
			String startDate = wrapper.getParameter("startDate");
			String endDate = wrapper.getParameter("endDate");
			// 排列内容
			String orderName = wrapper.getParameter
					("columns["+wrapper.getParameter("order[0][column]")+"][data]");
			// 排序内容
			String orderType = wrapper.getParameter("order[0][dir]");
			// 排序内容存在的场合
			if (orderName != null) {
				if ("stInterfaceName".equals(orderName)) {
					suffix = "ORDER BY ST_INTERFACE_NAME "+orderType.toUpperCase();
				} else if ("stExt1".equals(orderName)) {
					suffix = "ORDER BY ST_EXT1 "+orderType.toUpperCase();
				} else if ("stRemark".equals(orderName)){
					suffix = "ORDER BY ST_REMARK "+orderType.toUpperCase();
				} else if ("nmOrder".equals(orderName)){
					suffix = "ORDER BY NM_ORDER "+orderType.toUpperCase();
				} else if ("dtCreate".equals(orderName)) {
					suffix = "ORDER BY DT_CREATE "+orderType.toUpperCase();
				}
			}
			conds = t4r.toConditions(ApidocInterface.class);
			if(stModuleName != null){
				if(!StringUtils.trim(stModuleName).isEmpty()){
					conds.add(new Condition("ST_EXT1",Condition.OT_LIKE,stModuleName));
				}
			}
			if(stInterfaceName != null){
				if(!StringUtils.trim(stInterfaceName).isEmpty()){
					conds.add(new Condition("ST_INTERFACE_NAME",Condition.OT_LIKE,stInterfaceName));
				}
			}
			// 开始时间存在的场合
			if (startDate != null) {
				if (!StringUtils.trim(startDate).isEmpty()) {
					conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,Timestamp.valueOf(startDate + " 00:00:00")));
				}
			}
			if (endDate != null) {
				if (!StringUtils.trim(endDate).isEmpty()) {
					conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,Timestamp.valueOf(endDate + " 23:59:59")));
				}
			}
			// 每页长度
			String length = wrapper.getParameter("length");
			// 页面长度
			if (length != null) {
				pageSize = Integer.valueOf(length);
			}
			if (wrapper.getParameter("start") != null) {
				// 开始页
				int start = Integer.valueOf(wrapper.getParameter("start"));
				// 第一页的场合
				if ( start != 0) {
					// 当前页
					currentPage = Integer.valueOf(start)/pageSize+1;	
				}
			}
			Log.info("每页长度:"+pageSize+"当前页:"+currentPage);
		}
		
		return apidocInterfaceDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link ApidocInterface#ST_INTERFACE_ID}删除接口
	 * 
	 * @param stInterfaceId
	 *            接口主键 {@link ApidocInterface#ST_INTERFACE_ID}
	 */
	@Override
	public void remove(String[] stInterfaceId) {
		if (stInterfaceId.length == 0)
			throw new NullPointerException("Parameter stInterfaceId cannot be null.");
		// 删除
		for (String menuId : stInterfaceId) {
			// 接口Id不为空的场合
			if (!StringUtils.trimToEmpty(menuId).isEmpty()) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_INTERFACE_ID", Condition.OT_EQUAL, menuId));
				// 删除接口信息
				apidocInterfaceDao.delete(conds);
				//删除关联表信息
				apidocModlnterDao.delete(conds);
			}
		}
	}

	/**
	 * 保存或更新接口
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 接口实例
	 */
	@Override
	public ApidocInterface saveOrUpdate(HttpServletRequest req) {
		//获取参数
		ApidocInterface apidocInterface = null;
		RequestWrapper wrapper;
		try{
			apidocInterface = new ApidocInterface();
			// 获取传递对象参数
			Request2BeanHelper.req2Bean(req, apidocInterface);
			wrapper = new RequestWrapper(req);
			
			String stModuleId = wrapper.getParameter(ApidocInterface.ST_MODULE_ID); 
			if (StringUtils.trimToEmpty(stModuleId).isEmpty()) {
				throw new NullPointerException("接口所属模块不能为空");
			}
			
			// 模块目录集合
			String[] moduleId = stModuleId.split(",");
			ApidocInterface oldApidocInterface = null;
			// 接口ID不存在的场合
			if (!StringUtils.trimToEmpty(apidocInterface.getStInterfaceId()).isEmpty()) {
				oldApidocInterface = apidocInterfaceDao.get(apidocInterface.getStInterfaceId());
			}
			if (oldApidocInterface == null) {
				apidocInterface.setStInterfaceId(UUID.randomUUID().toString());
				// 插入关联数据
				apidocModlnterDao.add(apidocInterface.getStInterfaceId(),moduleId);
				apidocInterface.setDtCreate(new Timestamp(System.currentTimeMillis()));
				apidocInterfaceDao.add(apidocInterface);
			}else{
				Map<String, Object> updateMap =new  HashMap<String,Object>();
				// 更新条件
				Conditions conds = Conditions.newAndConditions();
				// 更新接口信息
				updateMap.put("ST_INTERFACE_NAME", apidocInterface.getStInterfaceName());
				updateMap.put("ST_URL", apidocInterface.getStUrl());
				updateMap.put("CL_REMARK", apidocInterface.getClRemark());
				updateMap.put("ST_METHOD", apidocInterface.getStMethod());
				updateMap.put("CL_REQUEST_PARAM", apidocInterface.getClRequestParam());
				updateMap.put("CL_REQUEST_EXAM", apidocInterface.getClRequestExam());
				updateMap.put("CL_RESPONSE_PARAM", apidocInterface.getClResponseParam());
				updateMap.put("CL_RESPONSE_EXAM", apidocInterface.getClResponseExam());
				updateMap.put("ST_MODULE_ID", apidocInterface.getStModuleId());
				updateMap.put("NM_STATUS", apidocInterface.getNmStatus());
				updateMap.put("NM_ORDER", apidocInterface.getNmOrder());
				updateMap.put("DT_UPDATE", new Timestamp(System.currentTimeMillis()));
				updateMap.put("NM_VERSION", apidocInterface.getNmVersion());
				updateMap.put("ST_EXT1", apidocInterface.getStExt1());
				updateMap.put("ST_EXT2", apidocInterface.getStExt2());
				//接口更新
			   	conds.add(new Condition("ST_INTERFACE_ID", Condition.OT_EQUAL, apidocInterface.getStInterfaceId()));
			   	apidocInterfaceDao.update(updateMap, conds);
				// 删除关联表
				conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_INTERFACE_ID", Condition.OT_EQUAL, apidocInterface.getStInterfaceId()));
				apidocModlnterDao.delete(conds);
				// 重新插入关联数据
				apidocModlnterDao.add(apidocInterface.getStInterfaceId(),moduleId);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return apidocInterface;
	}
	 
	public List<ApidocInterface> queryInterfaceAndLink(String stInterfaceId){
		 Conditions conds = Conditions.newAndConditions();
		// 接口ID不存在的场合
		if (!StringUtils.trimToEmpty(stInterfaceId).isEmpty()) {
			conds.add(new Condition("a.ST_INTERFACE_ID", Condition.OT_EQUAL, stInterfaceId));
		}
		 return apidocInterfaceDao.queryInterfaceAndLink(conds, null);
	 }
	
	@Override
	public List<ApidocInterface> queryByModuleId(String stModuleId) {
		return apidocInterfaceDao.queryInterfaceByLink(stModuleId);
	}

	@Override
	public void copy(String[] stInterfaceId) {
		if (stInterfaceId.length == 0)
			throw new NullPointerException("Parameter stInterfaceId cannot be null.");
		// 复制
		for (String Id : stInterfaceId) {
			ApidocInterface info = apidocInterfaceDao.get(Id);
			if(info != null){
				String name = info.getStInterfaceName().toString() + "-副本";
				info.setStInterfaceId(UUID.randomUUID().toString());
				info.setStInterfaceName(name.toString());
				info.setStModuleId("");
				info.setStExt1("");
				apidocInterfaceDao.add(info);
			}
		}
	}
	
	/**
	 * 获取模块目录
	 * 
	 */
	@Override
	public List<ApidocModule> getModuleList() {
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_PARENT_ID", Condition.OT_UNEQUAL, null));
		return apidocModuleDao.query(conds, " order by ST_MODULE_NAME");
	}
	
	
	@Autowired
	private ApidocInterfaceDao apidocInterfaceDao;
	@Autowired
	private ApidocModInterDao apidocModlnterDao;
	@Autowired
	private ApidocModuleDao apidocModuleDao;
	
}
