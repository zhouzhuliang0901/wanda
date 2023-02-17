package com.wondersgroup.sms.user.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.wondersgroup.sms.organ.service.SmsOrganService;
import com.wondersgroup.sms.user.bean.SmsUser;
import com.wondersgroup.sms.user.bean.SmsUserLink;
import com.wondersgroup.sms.user.dao.SmsUserDao;
import com.wondersgroup.sms.user.service.UserService;
import com.wondersgroup.wdf.dao.UacItemInfoTwo;
import coral.base.util.RequestWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reindeer.base.bean.WdfResult;
import reindeer.base.utils.JsonUtils;
import wfc.facility.tool.autocode.PaginationArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户表 web层控制器
 *
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SmsUserDao smsUserDao;

    /**
     * 查看
     *
     * @param req
     * @param res
     * @return
     * @throws IOException
     */
    @RequestMapping("/sms/user/info")
    public WdfResult info(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        RequestWrapper wrapper = new RequestWrapper(req);
        String stUserId = wrapper.getParameter(SmsUser.ST_USER_ID);
        SmsUser smsUser = userService.get(stUserId);
        return WdfResult.getResult().success().setData(JsonUtils.toJson(smsUser));
    }

    /**
     * 列表
     *
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/sms/user/list")
    public WdfResult list(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        WdfResult result = WdfResult.getSuccessResult();
        try {
            RequestWrapper wrapper = new RequestWrapper(req);
            PaginationArrayList<SmsUser> list = userService.query(wrapper);
            JsonNode node=JsonUtils.toJson(list, SmsUser.class);
            result.setData(node);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.failed().setMsg(ex.getMessage());
        }
        return result;
    }

    /**
     * 保存
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/sms/user/save")
    public WdfResult save(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        WdfResult result = WdfResult.getSuccessResult();
        try {
            RequestWrapper wrapper = new RequestWrapper(req);
            SmsUser smsUser = userService.saveOrUpdate(wrapper);
            if (smsUser != null)
                result.success().setMsg("用户表保存成功");
        } catch (Exception ex) {
            ex.printStackTrace();
            result.failed().setMsg(ex.getMessage());
        }
        return result;
    }

    /**
     * 删除 批量删除
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/sms/user/remove")
    public WdfResult remove(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        WdfResult result = WdfResult.getSuccessResult();
        try {
            RequestWrapper wrapper = new RequestWrapper(req);
            // 样表ID
            String[] userIdList = wrapper.getParameterValues(SmsUser.ST_USER_IDS);
            if (userIdList.length == 0) {
                String userId = wrapper.getParameter("stUserId");
                if (!StringUtils.isBlank(userId)) {
                    userIdList = new String[1];
                    userIdList[0] = userId;
                } else {
                    throw new NullPointerException("用户ID不能为空");
                }
            }
            userService.remove(userIdList);
            result.success().setMsg("用户信息表删除成功");
        } catch (Exception ex) {
            ex.printStackTrace();
            result.failed().setMsg(ex.getMessage());
        }
        return result;
    }

    /**
     * 查找用户关联信息
     *
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/sms/user/queryUserLink")
    public WdfResult queryUserLink(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        RequestWrapper wrapper = new RequestWrapper(req);
        // SmsUser.ST_USER_ID
        String stUserId = wrapper.getParameter(SmsUser.ST_USER_ID);
        SmsUser smsUser = userService.get(stUserId);
        String userName = smsUser.getStUserName();
        String userId = smsUser.getStUserId();
        SmsUserLink userLink = userService.queryUserLink(stUserId);
        userLink.setUserName(userName);
        userLink.setUserId(userId);
        return WdfResult.getResult().success().setData(JsonUtils.toJson(userLink));
        /*JSONObject obj = new JSONObject();
        // 返回数据
        obj.put("data", userLink);
        EasyUIHelper.writeResponse(res, obj.toString());*/
    }

    /**
     * 添加用户关联信息
     *
     * @param req
     * @param res
     * @throws IOException
     */
    @RequestMapping("/sms/user/addUserLink")
    public WdfResult addUserLink(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        WdfResult result = WdfResult.getSuccessResult();
        try {
            RequestWrapper wrapper = new RequestWrapper(req);
            // 添加用户角色
            userService.addUserLink(wrapper);
            // 保存成功
            result.success().setMsg("关联信息保存成功");

        } catch (Exception ex) {
            ex.printStackTrace();
            result.failed().setMsg(ex.getMessage());
        }
        return result;
    }

    /**
     * 修改用户密码
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/sms/user/editPw")
    public WdfResult editPW(HttpServletRequest req, HttpServletResponse res){
        WdfResult result = WdfResult.getSuccessResult();
        try {
            RequestWrapper wrapper = new RequestWrapper(req);
            String st_login_name = wrapper.getParameter("ST_LOGIN_NAME");
            String passWord = wrapper.getParameter("ST_PASSWORD");
            SmsUser user = smsUserDao.getUserName(st_login_name);
            user.setStPassword(passWord);
            smsUserDao.update(user);
            result.setData(user);
        }catch (Exception e){
            e.printStackTrace();
            result.failed().setMsg(e.getMessage());
        }
        return result;
    }

    //除去与事项组关联的人员列表
    @RequestMapping("/sms/user/queryNoUserLinkList")
    public WdfResult queryNoUserLink(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        WdfResult result = WdfResult.getSuccessResult();
        try {
            RequestWrapper wrapper = new RequestWrapper(req);
            PaginationArrayList<SmsUser> list = userService.queryNoUserLink(wrapper);
            result.setData(JsonUtils.toJson(list, SmsUser.class));
        } catch (Exception ex) {
            ex.printStackTrace();
            result.failed().setMsg(ex.getMessage());
        }
        return result;
    }

}
