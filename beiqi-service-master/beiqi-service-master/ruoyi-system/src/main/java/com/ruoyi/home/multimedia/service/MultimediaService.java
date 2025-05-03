package com.ruoyi.home.multimedia.service;

import com.ruoyi.common.core.page.TableDataInfoT;
import com.ruoyi.home.multimedia.domain.Multimedia;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 多媒体 service
 *
 * @author liuzhuoming
 */
public interface MultimediaService {

    TableDataInfoT<Multimedia> page(Integer type, Integer pageNum, Integer pageSize);

    Map<String, List<Multimedia>> page2(Integer type);

    Map<String, Object> page3(String filePath, Long pageNum, Long pageSize);
}
