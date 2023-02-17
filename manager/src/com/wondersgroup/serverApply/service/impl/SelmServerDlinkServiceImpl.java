package com.wondersgroup.serverApply.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.serverApply.bean.SelmServerDlink;
import com.wondersgroup.serverApply.dao.SelmServerDlinkDao;
import com.wondersgroup.serverApply.service.SelmServerDlinkService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;

/**
 * 服务关联设备业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class SelmServerDlinkServiceImpl implements SelmServerDlinkService {

	/**
	 * 根据主键 {@link SelmServerDlink#ST_APPLY_ID} {@link SelmServerDlink#ST_MACHINE_ID}获取服务关联设备
	 * 
	 * @param stApplyId
	 *            服务关联设备主键 {@link SelmServerDlink#ST_APPLY_ID}
	 * @param stMachineId
	 *            服务关联设备主键 {@link SelmServerDlink#ST_MACHINE_ID}
	 * @return 服务关联设备实例
	 */
	@Override
	public SelmServerDlink get(String stApplyId, String stMachineId) {
		if (StringUtils.trimToEmpty(stApplyId).isEmpty())
			throw new NullPointerException("Parameter stApplyId cannot be null.");
		if (StringUtils.trimToEmpty(stMachineId).isEmpty())
			throw new NullPointerException("Parameter stMachineId cannot be null.");
		return selmServerDlinkDao.get(stApplyId, stMachineId);
	}

	/**
	 * 查询服务关联设备列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 服务关联设备列表
	 */
	@Override
	public PaginationArrayList<SelmServerDlink> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(SelmServerDlink.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return selmServerDlinkDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link SelmServerDlink#ST_APPLY_ID} {@link SelmServerDlink#ST_MACHINE_ID}删除服务关联设备
	 * 
	 * @param stApplyId
	 *            服务关联设备主键 {@link SelmServerDlink#ST_APPLY_ID}
	 * @param stMachineId
	 *            服务关联设备主键 {@link SelmServerDlink#ST_MACHINE_ID}
	 */
	@Override
	public void remove(String stApplyId, String stMachineId) {
		if (StringUtils.trimToEmpty(stApplyId).isEmpty())
			throw new NullPointerException("Parameter stApplyId cannot be null.");
		if (StringUtils.trimToEmpty(stMachineId).isEmpty())
			throw new NullPointerException("Parameter stMachineId cannot be null.");
		selmServerDlinkDao.delete(stApplyId, stMachineId);
	}

	/**
	 * 保存或更新服务关联设备
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 服务关联设备实例
	 */
	@Override
	public List<SelmServerDlink> saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		
		String stApplyId = wrapper.getParameter("stApplyServerId");
		JSONArray stDeviceArray = JSONArray.fromObject( wrapper.getParameter("stDeviceIdList"));
		System.out.println(stDeviceArray.toString());
		List<SelmServerDlink> dlink = new ArrayList<SelmServerDlink>();
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_APPLY_ID", Condition.OT_LIKE, stApplyId));
		if (!StringUtils.trimToEmpty(stApplyId).isEmpty() && stDeviceArray.size() > 0) {
			for(int i=0; i<stDeviceArray.size(); i++){
				SelmServerDlink newSelmServerDlink = new SelmServerDlink();
				newSelmServerDlink.setStApplyId(stApplyId);
				newSelmServerDlink.setStMachineId(stDeviceArray.getString(i));
				newSelmServerDlink.setNmStatus(new BigDecimal(0));
				newSelmServerDlink.setDtCreate(new Timestamp(System.currentTimeMillis()));
				selmServerDlinkDao.add(newSelmServerDlink);
				dlink.add(newSelmServerDlink);
			}
		}
		return dlink;
	}

	@Autowired
	private SelmServerDlinkDao selmServerDlinkDao;

}
