package com.ruoyi.home.contact.service;

import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.home.contact.domain.ContactUs;

import java.util.Date;
import java.util.List;

/**
 * 联系我们
 *
 * @author liuzhuoming
 */
public interface ContactUsService {
    void insert(ContactUs contactUs);

    List<ContactUs> list(Date startTime, Date endTime);

    TableDataInfoT<ContactUs> page(Integer pageNum, Integer pageSize, Date startTime, Date endTime);
}
