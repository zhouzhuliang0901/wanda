package com.wondersgroup.wdf.uacItemInfo.service.impl;

import com.wondersgroup.wdf.uacItemInfo.dao.ItemsSystem;
import com.wondersgroup.wdf.uacItemInfo.dao.ItemsSystemDao;
import com.wondersgroup.wdf.uacItemInfo.service.ItemsSystemService;
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
 * 事态信息&系统信息关联接口实现
 * @Author: whp
 * @Date: 2021/7/28 9:51
 */

@Service
@Transactional
public class ItemsSystemServiceImpl implements ItemsSystemService {

    @Autowired
    private ItemsSystemDao itemsSystemDao;

    /**
     * 根据 ItemsSystem 中 stItemId获取事项信息
     * @param stItemId
     * @return
     */
    @Override
    public List<ItemsSystem> getBystItemId(String stItemId) {
        if (StringUtils.trimToEmpty(stItemId).isEmpty())
            throw new NullPointerException("Parameter stItemId cannot be null.");
        return itemsSystemDao.getBystItemId(stItemId);
    }

    @Override
    public List<ItemsSystem> getBystWebSystemId(String stWebSystemId) {
        if (StringUtils.trimToEmpty(stWebSystemId).isEmpty())
            throw new NullPointerException("Parameter stItemId cannot be null.");
        return itemsSystemDao.getBystWebSystemId(stWebSystemId);
    }


    /**
     * 事项信息&系统信息关联列表
     * @param wrapper
     * @return
     */
    @Override
    public PaginationArrayList<ItemsSystem> query(RequestWrapper wrapper) {

        Conditions conds = Conditions.newAndConditions();
        String suffix = StringUtils.EMPTY;
        int pageSize = Integer.MAX_VALUE / 2;
        int currentPage = 1;
        if(wrapper != null){
            Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
            conds = t4r.toConditions(ItemsSystem.class);
            EasyUIHelper.Page page = EasyUIHelper.getPage(wrapper);
            currentPage = page.getPageSize();
            suffix = page.getSuffix();
        }
        return itemsSystemDao.query(conds,suffix,pageSize,currentPage);
    }
}
