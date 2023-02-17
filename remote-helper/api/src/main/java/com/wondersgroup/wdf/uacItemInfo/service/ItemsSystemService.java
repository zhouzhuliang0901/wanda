package com.wondersgroup.wdf.uacItemInfo.service;

import com.wondersgroup.wdf.uacItemInfo.dao.ItemsSystem;
import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

import java.util.List;

/**
 * 事项信息&系统信息关联接口
 * @Author: whp
 * @Date: 2021/7/28 9:46
 */
public interface ItemsSystemService {

    /**
     * 根据 ItemsSystem 中 stItemId获取事项信息
     * @param stItemId
     * @return
     */
    List<ItemsSystem> getBystItemId(String stItemId);

    /**
     * 根据 ItemsSystem 中 stWebSystemId获取事项信息
     * @param stWebSystemId
     * @return
     */
    List<ItemsSystem> getBystWebSystemId(String stWebSystemId);

    /**
     * 事态信息&系统信息关联列表查询
     * @param wrapper
     * @return
     */
    PaginationArrayList<ItemsSystem> query(RequestWrapper wrapper);

}
