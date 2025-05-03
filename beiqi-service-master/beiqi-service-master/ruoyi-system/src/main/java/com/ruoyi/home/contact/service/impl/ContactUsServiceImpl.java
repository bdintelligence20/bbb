package com.ruoyi.home.contact.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.home.contact.domain.ContactUs;
import com.ruoyi.home.contact.mapper.ContactUsMapper;
import com.ruoyi.home.contact.service.ContactUsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 联系我们
 *
 * @author liuzhuoming
 */
@Service
@AllArgsConstructor
@Slf4j
public class ContactUsServiceImpl implements ContactUsService {
    private ContactUsMapper contactUsMapper;

    @Override
    public void insert(ContactUs contactUs) {
        contactUs.setCreateTime(new Date());
        contactUsMapper.insert(contactUs);
    }

    @Override
    public List<ContactUs> list(Date startTime, Date endTime) {
        LambdaQueryWrapper<ContactUs> contactUsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        contactUsLambdaQueryWrapper
                .lt(endTime != null, ContactUs::getCreateTime, endTime)
                .ge(startTime != null, ContactUs::getCreateTime, startTime);
        return contactUsMapper.selectList(contactUsLambdaQueryWrapper);
    }

    @Override
    public TableDataInfoT<ContactUs> page(Integer pageNum, Integer pageSize, Date startTime, Date endTime) {
        LambdaQueryWrapper<ContactUs> contactUsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        contactUsLambdaQueryWrapper
                .lt(endTime != null, ContactUs::getCreateTime, endTime)
                .gt(startTime != null, ContactUs::getCreateTime, startTime)
                .orderByDesc(ContactUs::getCreateTime);
        Page<ContactUs> page = new Page<>(pageNum, pageSize);
        Page<ContactUs> pageRes = contactUsMapper.selectPage(page, contactUsLambdaQueryWrapper);
        return new TableDataInfoT<>(pageRes);
    }
}
