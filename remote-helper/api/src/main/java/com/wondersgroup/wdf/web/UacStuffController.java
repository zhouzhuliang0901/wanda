package com.wondersgroup.wdf.web;

import com.wondersgroup.wdf.dao.UacStuff;
import com.wondersgroup.wdf.service.UacStuffService;
import com.wondersgroup.wdf.uacItemStuff.dao.UacItemStuff;
import com.wondersgroup.wdf.uacItemStuff.dao.UacItemStuffDao;
import com.wondersgroup.wdf.uacItems.dao.UacItems;
import com.wondersgroup.wdf.uacItemsLink.dao.UacItemsLink;
import com.wondersgroup.wdf.uacItemsLink.dao.UacItemsLinkDao;
import coral.base.util.RequestWrapper;
import coral.widget.data.DataSet;
import coral.widget.data.ExtAjaxReturnMessage;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIJsonConverter;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 材料信息 web层控制器
 *
 * @author scalffold
 *
 */
@RestController
public class UacStuffController {

	@RequestMapping("/wdf/uacStuff/list")
	public WdfResult list(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			PaginationArrayList<UacStuff> list =uacStuffService.query(wrapper);
			result.setData(JsonUtils.toJson(list,UacStuff.class));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacStuff/remove")
	public WdfResult logicDelete(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			// 角色ID
			String[] stStuffId = wrapper.getParameterValues("ST_STUFF_ID[]");
			uacStuffService.logicDelete(stStuffId);
			result.success().setMsg("删除成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	@RequestMapping("/wdf/uacStuff/save")
	public WdfResult save(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		WdfResult result = WdfResult.getSuccessResult();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			UacStuff uacStuff = uacStuffService.saveOrUpdate(wrapper);
			if (uacStuff != null)
				result.success().setMsg("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			result.failed().setMsg(ex.getMessage());
		}
		return result;
	}

	/**
	 * 根据主题id查询材料信息列表
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/wdf/uacStuff/getStuffListByItemId")
	public JSONObject getStuffListByItemId(HttpServletRequest req, HttpServletResponse res){
		WdfResult result = WdfResult.getSuccessResult();
		JSONObject jsonObject = new JSONObject();
		try {
			RequestWrapper wrapper = new RequestWrapper(req);
			String st_items_id = wrapper.getParameter("ST_ITEMS_ID");
			Conditions cons = Conditions.newAndConditions();
			cons.add(new Condition(UacItemsLink.ST_ITEMS_ID,Condition.OT_EQUAL,st_items_id));
			//事项列表
			List<UacItemsLink> bystItemsId = uacItemsLinkDao.getBystItemsId(st_items_id);
			//事项材料
			ArrayList<UacItemStuff> uacItemStuffs = new ArrayList<>();
			//材料信息
			ArrayList<UacStuff> uacStuffs = new ArrayList<>();

			bystItemsId.forEach( item ->{
				if (item.getStItemId() != null){
					List<UacItemStuff> byst_item_id = uacItemStuffDao.getByst_item_id(item.getStItemId());
					uacItemStuffs.addAll(byst_item_id);
				}else {
					uacItemStuffs.add(null);
				}
			});

			//对uacItemStuffs去重
			uacItemStuffs.stream().collect(Collectors.collectingAndThen(
					Collectors.toCollection(() ->
							new TreeSet<UacItemStuff>(Comparator.comparing(UacItemStuff::getStItemStuffId))),ArrayList::new));

			//遍历材料信息列表
			uacItemStuffs.forEach(item -> {
				if (item.getStStuffId() != null){
					UacStuff uacStuff = uacStuffService.get(item.getStStuffId());
					uacStuffs.add(uacStuff);
				}else {
					uacStuffs.add(null);
				}
			});
			jsonObject.put("list",uacStuffs);
			jsonObject.put("msg",true);
		}catch (Exception e){
			e.printStackTrace();
			result.failed().setMsg(e.getMessage());
			jsonObject.put("meg",e.getMessage());
		}

		return jsonObject;
	}

	@Autowired
	private UacStuffService uacStuffService;

	@Autowired
	private UacItemStuffDao uacItemStuffDao;

	@Autowired
	private UacItemsLinkDao uacItemsLinkDao;


}
