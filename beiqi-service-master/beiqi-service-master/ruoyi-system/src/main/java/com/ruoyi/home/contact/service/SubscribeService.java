package com.ruoyi.home.contact.service;

import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.home.contact.domain.Subscribe;
import com.ruoyi.website.domain.vo.NewsVO;

/**
 * 订阅
 *
 * @author liuzhuoming
 */
public interface SubscribeService {
    void insert(Subscribe subscribe);

    TableDataInfoT<Subscribe> page(Integer pageName, Integer pageSize, Integer status, String keyword);

    void updateStatus(Integer id, Integer status, String reason);

    void sendNewsEmailToAvailableSubscribers(NewsVO newsVO);
}
