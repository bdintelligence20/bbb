package com.ruoyi.home.contact.service;

import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.home.contact.domain.MediaContact;

import java.util.Date;

/**
 * 媒体联系
 *
 * @author liuzhuoming
 */
public interface MediaContactService {

    void insert(MediaContact mediaContact);

    TableDataInfoT<MediaContact> page(Integer pageNum, Integer pageSize, Date startTime, Date endTime);
}
