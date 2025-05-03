package com.ruoyi.website.service.impl;


import com.ruoyi.common.constant.WebConfigConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.website.domain.WebConfig;
import com.ruoyi.website.domain.vo.WebConfigVO;
import com.ruoyi.website.mapper.WebConfigMapper;
import com.ruoyi.website.service.IWebConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 站点配置Service业务层处理
 *
 * @author thh
 * @date 2021-06-08
 */
@Service
public class WebConfigServiceImpl implements IWebConfigService {
    @Autowired
    private WebConfigMapper webConfigMapper;

    @Autowired
    private RedisCache redisCache;


    @Override
    public WebConfig getWebConfig() {
        WebConfig webConfig = webConfigMapper.selectById(WebConfigConstants.WEB_CONFIG_ID);
        webConfig.setGithub("");
        webConfig.setGitee("");
        webConfig.setQqNumber("");
        webConfig.setWeChat("");
        webConfig.setQiNiuFileBaseUrl("");
        webConfig.setLocalFileBaseUrl("");
        webConfig.setEmail("");
        webConfig.setEmailUserName("");
        webConfig.setEmailPassword("");
        webConfig.setSmtpAddress("");
        webConfig.setSmtpPort("");
        webConfig.setQqGroup("");
        return webConfig;
    }

    @Override
    public int updateWebConfig(WebConfigVO webConfigVO) {
        int count = 0;
        if (StringUtils.isEmpty(webConfigVO.getConfigId())) {
            WebConfig webConfig = new WebConfig();
            // 设置网站配置【使用Spring工具类提供的深拷贝】
            BeanUtils.copyProperties(webConfigVO, webConfig);
            webConfigMapper.insert(webConfig);
        } else {
            WebConfig webConfig = webConfigMapper.selectById(WebConfigConstants.WEB_CONFIG_ID);
            // 更新网站配置【使用Spring工具类提供的深拷贝】
            BeanUtils.copyProperties(webConfigVO, webConfig);
            webConfig.setUpdateTime(new Date());
            count = webConfigMapper.updateById(webConfig);
        }
        return count;
    }
}
