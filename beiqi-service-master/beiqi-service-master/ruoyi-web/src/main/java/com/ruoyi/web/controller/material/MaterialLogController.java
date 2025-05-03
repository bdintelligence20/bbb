package com.ruoyi.web.controller.material;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.http.HttpHelper;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.common.utils.json.JsonUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.material.service.IMaterialLogService;
import com.ruoyi.system.domain.MaterialLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * materialLogController
 * 
 * @author thh
 * @date 2023-11-15
 */
@RestController
@RequestMapping("/dealer-download/system/materialLog")
public class MaterialLogController extends BaseController
{
    @Autowired
    private IMaterialLogService materialLogService;

    @Autowired
    private TokenService tokenService;

    /**
     * 新增materialLog
     */
    @PostMapping
    public AjaxResult add(HttpServletRequest request, HttpServletResponse response)
    {
        final LoginUser loginUser = tokenService.getLoginUser(request);
        final String clientIpAddress = IpUtils.getIpAddr(ServletUtils.getRequest());
        final String servletPath = request.getServletPath();
        final String method = request.getMethod();
        final Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder sb = new StringBuilder();
        MaterialLog log = new MaterialLog();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            for (String value : entry.getValue()) {
                sb.append(entry.getKey()).append('=').append(value).append('&');
                if (entry.getKey().equals("type")){
                    log.setType(value);
                }else if (entry.getKey().equals("model")){
                    log.setModel(value);
                }else if (entry.getKey().equals("downloadLink")){
                    log.setDownloadLink(value);
                }
            }
        }
        String parameterString = sb.toString();
        final String bodyString = HttpHelper.getBodyString(request);
        final String resStr = response.toString();


        log.setUserId(loginUser != null ? loginUser.getUserId() : 0L);
        log.setIp(clientIpAddress);
        log.setMethod(method);
        log.setPath(servletPath);
        log.setUrlParameter(parameterString);
        log.setBodyParameter(bodyString);
        log.setResponseData(resStr);
        return toAjax(materialLogService.insertMaterialLog(log));
    }

}
