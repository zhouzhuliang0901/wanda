package com.wondersgroup.wdf.uacItemInfo.service;

import com.wondersgroup.wdf.uacItemInfo.dao.ItemsView;
import coral.base.util.RequestWrapper;
import wfc.facility.tool.autocode.PaginationArrayList;

import java.util.List;

/**
 * 事项信息&业态信息关联业务接口
 * @Author: whp
 * @Date: 2021/7/27 15:13
 */
public interface ItemsViewService {


    /**
     * 根据 ItemsView 中 stItemId获取列表信息
     * @param stItemId
     * @return
     */
    List<ItemsView> getBystItemId(String stItemId);

    /**
     * 更具 ItemsView 中的stItemsId获取列表信息
     * @param stItemsId
     * @return
     */
    List<ItemsView> getBystItemsId(String stItemsId);

    /**
     * 事项&业态i信息关联查询列表
     * @param wrapper
     * @return 关联查询列表
     */
    PaginationArrayList<ItemsView> query(RequestWrapper wrapper);


}
