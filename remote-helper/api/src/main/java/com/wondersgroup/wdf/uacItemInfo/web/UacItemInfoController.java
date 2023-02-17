package com.wondersgroup.wdf.uacItemInfo.web;

import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.wdf.uacItemInfo.dao.ItemsSystem;
import com.wondersgroup.wdf.uacItemInfo.dao.ItemsView;
import com.wondersgroup.wdf.uacItemInfo.dao.UacItemInfo;
import com.wondersgroup.wdf.uacItemInfo.dao.UacItemInfoDao;
import com.wondersgroup.wdf.uacItemInfo.service.ItemsSystemService;
import com.wondersgroup.wdf.uacItemInfo.service.ItemsViewService;
import com.wondersgroup.wdf.uacItemInfo.service.UacItemInfoService;
import com.wondersgroup.wdf.uacItemSystem.service.UacItemSystemService;
import com.wondersgroup.wdf.uacItems.dao.UacItems;
import com.wondersgroup.wdf.uacItemsLink.service.UacItemsLinkService;
import com.wondersgroup.wdf.uacWebSystem.dao.UacWebSystem;
import coral.base.util.RequestWrapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 事项信息 web层控制器
 *
 * @author scalffold
 */
@RestController
public class UacItemInfoController {

//    @RequestMapping("/wdf/item/edit")
//    public ModelAndView edit(HttpServletRequest req, HttpServletResponse res)
//            throws IOException {
//        RequestWrapper wrapper = new RequestWrapper(req);
//        // UacItemInfo.ST_ITEM_ID
//        String stItemId = wrapper.getParameter(UacItemInfo.ST_ITEM_ID);
//        if (!StringUtils.trimToEmpty(stItemId).isEmpty()) {
//            UacItemInfo uacItemInfo = uacItemInfoService.get(stItemId);
//            req.setAttribute(UacItemInfo.UAC_ITEM_INFO, uacItemInfo);
//        }
//        return new ModelAndView("/uacItemInfo/edit.jsp");
//    }

    /**
     * 获取常用事项
     * @return
     */
    @RequestMapping("/wdf/uacItemInfo/getItems")
    public List<UacItemInfo> getItems() {
            Conditions conds = Conditions.newAndConditions();
            List<UacItemInfo> uacItemInfos = uacItemInfoDao.queryItems(conds, "");

        return uacItemInfos;
    }


    /***
     * 获取大事项
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/uacItemInfo/listBig.do")
    public List<UacItemInfo> listBig(HttpServletRequest req, HttpServletResponse res) {
        try {
            SmsUser user =  (SmsUser) SecurityUtils.getSubject().getPrincipal();
            String stUserId = user.getStUserId();

            RequestWrapper wrapper = new RequestWrapper(req);
            wrapper.addParam("stUserId",stUserId);
            List<UacItemInfo> list = uacItemInfoService.queryBig(wrapper);

            List<UacItemInfo> reList = new LinkedList<UacItemInfo>();

            UacItemInfo uacItemInfo = list.get(0);
            reList.add(uacItemInfo);
            String st_item_name = wrapper.getParameter("ST_ITEM_NAME");
            if (st_item_name.equals("")){
                for(UacItemInfo allUac : list){
                    int i = 1; //1加入  0不加入
                    for (UacItemInfo oneUac : reList) {
                        String stItemName = allUac.getStItemName();
                        String stItemName1 = oneUac.getStItemName();

                        if (stItemName.equals(stItemName1)) {
                            i = 0;
                            continue;
                        }
                    }
                    if (i == 1) {
                        reList.add(allUac);
                    }
                    if (reList.size() >= 10){
                        break;
                    }
                }
                return reList;
            }else {
                for (UacItemInfo allUac : list) {
                    int i = 1; //1加入  0不加入
                    for (UacItemInfo oneUac : reList) {
                        String stItemName = allUac.getStItemName();
                        String stItemName1 = oneUac.getStItemName();

                        if (stItemName.equals(stItemName1)) {
                            i = 0;
                            continue;
                        }
                    }
                    if (i == 1) {
                        reList.add(allUac);
                    }
                }
            }
            return reList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /***
     * 获取小事项
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/uacItemInfo/listSmall.do")
    public List<UacItemInfo> listSmall(HttpServletRequest req, HttpServletResponse res) {
        try {
            RequestWrapper wrapper = new RequestWrapper(req);
            String stItemName = wrapper.getParameter("ST_ITEM_NAME");
            List<UacItemInfo> list = uacItemInfoService.querySmall(stItemName);
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/wdf/uacItemInfo/item/info")
    public WdfResult info(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        RequestWrapper wrapper = new RequestWrapper(req);
        String stItemId = wrapper.getParameter(UacItemInfo.ST_ITEM_ID);
        UacItemInfo uacItemInfo = uacItemInfoService.get(stItemId);
        req.setAttribute(UacItemInfo.UAC_ITEM_INFO, uacItemInfo);
        return WdfResult.getResult().success().setData(JsonUtils.toJson(uacItemInfo));
    }

    @RequestMapping("/wdf/uacItemInfo/item/list")
    public WdfResult list(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        WdfResult result = WdfResult.getSuccessResult();
        try {
            RequestWrapper wrapper = new RequestWrapper(req);
            PaginationArrayList<UacItemInfo> list = uacItemInfoService.query(wrapper);
            result.setData(JsonUtils.toJson(list, UacItemInfo.class));
        } catch (Exception ex) {
            ex.printStackTrace();
            result.failed().setMsg(ex.getMessage());
        }
        return result;
    }

    @RequestMapping("/wdf/uacItemInfo/item/remove")
    public WdfResult remove(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        WdfResult result = WdfResult.getSuccessResult();
        try {
            RequestWrapper wrapper = new RequestWrapper(req);
            // UacItemInfo.ST_ITEM_ID
            String stItemId = wrapper.getParameter(UacItemInfo.ST_ITEM_ID);
            uacItemInfoService.remove(stItemId);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.failed().setMsg(ex.getMessage());
        }
        return result;
    }

    @RequestMapping("/wdf/uacItemInfo/item/save")
    public WdfResult save(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        WdfResult result = WdfResult.getSuccessResult();
        try {
            RequestWrapper wrapper = new RequestWrapper(req);
            UacItemInfo uacItemInfo = uacItemInfoService.saveOrUpdate(wrapper);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.failed().setMsg(ex.getMessage());
        }
        return result;
    }

    @RequestMapping("/wdf/uacItemInfo/item/infoAndItemsList")
    public WdfResult infoRelatingItemsList(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        WdfResult result = WdfResult.getSuccessResult();
        try {
            RequestWrapper wrapper = new RequestWrapper(req);
            PaginationArrayList<ItemsView> list = itemsViewService.query(wrapper);
            result.setData(JsonUtils.toJson(list, ItemsView.class));
        } catch (Exception ex) {
            ex.printStackTrace();
            result.failed().setMsg(ex.getMessage());
        }
        return result;
    }

    /**
     * 事项&业态关联  通过事项ID 查询列表
     * @param req
     * @param res
     * @return
     * @throws IOException
     */
    @RequestMapping("/wdf/uacItemInfo/item/infoAndItemsInfoBystItemId")
    public List<ItemsView> infoRelatingItemsInfoBystItemId(HttpServletRequest req,HttpServletResponse res)
            throws IOException {
        RequestWrapper wrapper = new RequestWrapper(req);
        String stItemId = wrapper.getParameter(UacItemInfo.ST_ITEM_ID);
        List<ItemsView> list = itemsViewService.getBystItemId(stItemId);
        return list;
    }

    /**
     * 事项&业态关联  通过业态ID 查询列表
     * @param req
     * @param res
     * @return
     * @throws IOException
     */
    @RequestMapping("/wdf/uacItemInfo/item/infoAndItemsInfoBystItemsId")
    public List<ItemsView> infoRelatingItemsInfoBystItemsId(HttpServletRequest req,HttpServletResponse res)
            throws IOException {
        RequestWrapper wrapper = new RequestWrapper(req);
        String stItemsId = wrapper.getParameter(UacItems.ST_ITEMS_ID);
        List<ItemsView> list = itemsViewService.getBystItemId(stItemsId);
        return list;
    }

    @RequestMapping("/wdf/uacItemInfo/item/infoAndWebList")
    public WdfResult infoRelatingWebSystem(HttpServletRequest req,HttpServletResponse res)
            throws IOException{
        WdfResult result = WdfResult.getSuccessResult();
        try {
            RequestWrapper wrapper = new RequestWrapper(req);
            PaginationArrayList<ItemsSystem> list = itemsSystemService.query(wrapper);
            result.setData(JsonUtils.toJson(list,ItemsSystem.class));
        } catch (Exception ex) {
            ex.printStackTrace();
            result.failed().setMsg(ex.getMessage());
        }
        return result;
    }

    /**
     * 事项&系统关联查询 通过事项ID查询列表
     * @param req
     * @param res
     * @return
     * @throws IOException
     */
    @RequestMapping("/wdf/uacItemInfo/item/infoAndWebInfoBystItemId")
    public List<ItemsSystem> infoRelatingWebInfoBystItemId(HttpServletRequest req,HttpServletResponse res)
            throws IOException {
        RequestWrapper wrapper = new RequestWrapper(req);
        String stItemId = wrapper.getParameter(ItemsSystem.ST_ITEM_ID);
        List<ItemsSystem> list = itemsSystemService.getBystItemId(stItemId);
        return list;
    }

    /**
     * 事项&系统关联查询 通过系统ID查询列表
     * @param req
     * @param res
     * @return
     * @throws IOException
     */
    @RequestMapping("/wdf/uacItemInfo/item/infoAndWebInfoBystWebSystemId")
    public List<ItemsSystem> infoRelatingWebInfoBystWebSystemId(HttpServletRequest req,HttpServletResponse res)
            throws IOException {
        RequestWrapper wrapper = new RequestWrapper(req);
        String stWebSystemId = wrapper.getParameter(UacWebSystem.ST_WEB_SYSTEM_ID);
        List<ItemsSystem> list = itemsSystemService.getBystWebSystemId(stWebSystemId);
        return list;
    }

    @RequestMapping("/wdf/uacItemInfo/item/addItems")
    public WdfResult addInfoAndItems(HttpServletRequest req,HttpServletResponse res)
            throws IOException {
        WdfResult result = WdfResult.getSuccessResult();
        RequestWrapper wrapper = new RequestWrapper(req);
        String stItemsId = wrapper.getParameter(UacItems.ST_ITEMS_ID);
        List<String> list = wrapper.getParameterValueList(UacItemInfo.ST_ITEM_ID);
        String[] str_stItemId = new String[list.size()];
        for (int i = 0;i < list.size() ;i++){
            str_stItemId[i] = list.get(i);
        }
        uacItemsLinkService.addItems(stItemsId,str_stItemId);
        return result;
    }

    @RequestMapping("/wdf/uacItemInfo/item/addWeb")
    public WdfResult addInfoAndWeb(HttpServletRequest req,HttpServletResponse res)
            throws IOException {
        WdfResult result = WdfResult.getSuccessResult();
        RequestWrapper wrapper = new RequestWrapper(req);
        String stItemId = wrapper.getParameter(UacItemInfo.ST_ITEM_ID);
        List<String> list = wrapper.getParameterValueList(UacWebSystem.ST_WEB_SYSTEM_ID);
        String[] str_stWebSystemId = new String[list.size()];
        for (int i = 0;i < list.size();i++){
            str_stWebSystemId[i] = list.get(i);
        }
        uacItemSystemService.addWeb(stItemId,str_stWebSystemId);
        return result;
    }


    @Autowired
    private ItemsSystemService itemsSystemService;

    @Autowired
    private UacItemInfoService uacItemInfoService;

    @Autowired
    private ItemsViewService itemsViewService;

    @Autowired
    private UacItemsLinkService uacItemsLinkService;

    @Autowired
    private UacItemSystemService uacItemSystemService;

    @Autowired
    private UacItemInfoDao uacItemInfoDao;
}
