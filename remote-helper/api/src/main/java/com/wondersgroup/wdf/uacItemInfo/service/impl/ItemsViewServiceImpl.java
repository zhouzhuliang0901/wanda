package com.wondersgroup.wdf.uacItemInfo.service.impl;

import com.wondersgroup.wdf.uacItemInfo.dao.ItemsView;
import com.wondersgroup.wdf.uacItemInfo.dao.ItemsViewDao;
import com.wondersgroup.wdf.uacItemInfo.service.ItemsViewService;
import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;

import coral.widget.utils.EasyUIHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;

import java.util.List;

/**
 * 事项信息&业态信息关联业务实现
 * @Author: whp
 * @Date: 2021/7/27 15:20
 */

@Service
@Transactional
public class ItemsViewServiceImpl implements ItemsViewService {

    @Autowired
    private ItemsViewDao itemsViewDao;

    /**
     * 根据 ItemsView 中 stItemId获取列表信息
     * @param stItemId
     * @return
     */
    @Override
    public List<ItemsView> getBystItemId(String stItemId) {
        if (StringUtils.trimToEmpty(stItemId).isEmpty())
            throw new NullPointerException("Parameter stItemId cannot be null.");
        return itemsViewDao.getBystItemId(stItemId);
    }

    /**
     * 根据 ItemsView 中 stItemsId获取列表信息
     * @param stItemsId
     * @return
     */
    @Override
    public List<ItemsView> getBystItemsId(String stItemsId) {
        if (StringUtils.trimToEmpty(stItemsId).isEmpty())
            throw new NullPointerException("Parameter stItemId cannot be null.");
        return itemsViewDao.getBystItemsId(stItemsId);
    }


    /**
     * 事项信息&业态信息关联列表
     * @param wrapper
     * @return 关联查询列表
     */
    @Override
    public PaginationArrayList<ItemsView> query(RequestWrapper wrapper) {
        Conditions conds = Conditions.newAndConditions();
        String suffix = StringUtils.EMPTY;
        int pageSize = Integer.MAX_VALUE / 2;
        int currentPage = 1;
        if (wrapper != null) {
            Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
                    wrapper);
            conds = t4r.toConditions(ItemsView.class);
            EasyUIHelper.Page page = EasyUIHelper.getPage(wrapper);
            pageSize = page.getPageSize();
            currentPage = page.getCurrentPage();
            suffix = page.getSuffix();
        }
        return itemsViewDao.query(conds,suffix,pageSize,currentPage);
    }
}
